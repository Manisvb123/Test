// create the controller and inject Angular's $scope
tlgApp.controller('sstsecondController',['$scope','internationalizationService','Constant.labels','$timeout','$http','$window', function($scope,internationalizationService,Constantlabels,$timeout,$http,$window) {
	$scope.labels_sst= {};
	jQuery.extend( true, $scope.labels_sst, internationalizationService.get(Constantlabels.sst));
	if($scope.labels_sst==null)
	{
		alert("Failed to render the labels! Please refresh the page . If problem persists, contact support.");
	}
	$scope.initialize = function() {
		$scope.isQuestionAnswered=false
		$scope.data={
			"request":  
			{
				"uid": "userid",
				"pid": "PR-001",
				"toolid": "SST-001",
				"templateid": "TMP-001",
				"pageid": "PG-001",
			}
		}
    }

    //Method used to select the option
    $scope.onOptionSelect = function (pdIndex,sdIndex,questionIndex,metricIndex,index,option) {
    	$scope.questionobject={
    		"Type":"Radio",
    		"dynamicid":"",
    		"questionid":"",
    		"option":option
    	}
    	$scope.questionobject.dynamicid=pdIndex.toString()+sdIndex.toString()+questionIndex.toString()+metricIndex.toString()+index.toString()
    	$scope.questionobject.questionid=pdIndex.toString()+sdIndex.toString()+questionIndex.toString()+metricIndex.toString()
    	if($scope.idList.length>0){
    		$scope.isIdFound=false
    		angular.forEach($scope.idList, function(id, idindex){
    			if(id.questionid===$scope.questionobject.questionid){
    				angular.forEach($scope.questionlist.PageQuestions[pdIndex].sdlist[sdIndex].qlist[questionIndex].metrics[metricIndex].Optlbl, function(options, idindex){
    					$scope.questionlist.PageQuestions[pdIndex].sdlist[sdIndex].qlist[questionIndex].metrics[metricIndex].Optlbl[idindex].selected=false
    				})
    				$scope.questionlist.PageQuestions[pdIndex].sdlist[sdIndex].qlist[questionIndex].metrics[metricIndex].Optlbl[index].selected=true
    				id.option=$scope.questionobject.option;
    				$scope.isIdFound=true
    			}
    		});
    		if($scope.isIdFound===false){
    			$scope.questionobject.option.selected=true
    			$scope.idList.push($scope.questionobject);	
    		}
    	}else{
    		$scope.questionobject.option.selected=true
    		$scope.idList.push($scope.questionobject);
    	}
    	if($scope.idList.length===$scope.totalMetricsintheCurrentPage){
    		$scope.isQuestionAnswered=true
    	}else{
    		$scope.isQuestionAnswered=false
    	}
    }

//Method to get first page questions
$scope.getquestiondata=function(){
   $scope.idList=[]
   $scope.data={
      "request":  
      {
         "uid": "userid",
         "pid": "PR-001",
         "toolid": "SST-001",
         "templateid": "TMP-001",
         "pageid": "PG-001"
     }
 }
 $http({
  method: "Post",
  url: "http://192.168.0.13:9090/TLG/GetQuestionnaire",
        		// url: "http://localhost:8080/TLG/GetQuestionnaire",
        		data:$scope.data,
        		headers: {'Content-Type':'application/json','Accept':'application/json'}

        	}).then(function(questionsummaryresponse) {
        		$scope.totalNumberofPages=parseInt(questionsummaryresponse.data.Questionnaire.numpgs);
        		if($scope.totalNumberofPages===1){
        			$scope.isLastPage=true
        			$scope.showPreviousButton=false
        		}else{
        			$scope.isLastPage=false
        		}
        		$scope.pageListIds=questionsummaryresponse.data.Questionnaire.pagelst
        		$http({
        			method: "Post",
        			url: "http://192.168.0.13:9090/TLG/GetQuestionnaireDetails",
        		// url: "http://localhost:8080/TLG/GetQuestionnaireDetails",
        		data:$scope.data,
        		headers: {'Content-Type':'application/json','Accept':'application/json'}

        	}).then(function(pagedetailsresponse) {
        		$scope.currentPageNo=parseInt(pagedetailsresponse.data.Page.pgno);
        		$scope.currentPageID=pagedetailsresponse.data.Page.pgid;
        		$scope.title=pagedetailsresponse.data.Page.commonInfo.title;
        		$scope.instruction=pagedetailsresponse.data.Page.commonInfo.inst;
        		$scope.Rmrks=pagedetailsresponse.data.Page.commonInfo.Rmrks;
               $http({
                  method: "Post",
                  url: "http://192.168.0.13:9090/TLG/GetPageDetails",
        		// url: "http://localhost:8080/TLG/GetPageDetails",
        		data:$scope.data,
        		headers: {'Content-Type':'application/json','Accept':'application/json'}

        	}).then(function(response) {
                $scope.data.request.uid=response.data.keys.uid;
                $scope.noQuestionsintheCurrentPage=parseInt(response.data.keys.no_of_questions);
                $scope.questionlist = response.data;
                 $scope.totalMetricsintheCurrentPage=0
                angular.forEach($scope.questionlist.PageQuestions, function(pd, pdindex){
                 angular.forEach(pd.sdlist, function(sd, sdindex){
                    angular.forEach(sd.qlist, function(question, questionindex){
                            angular.forEach(question.metrics, function(metric, metricindex){
                                $scope.totalMetricsintheCurrentPage=$scope.totalMetricsintheCurrentPage+1
                            angular.forEach(metric.Optlbl, function(options, optionsindex){
                                if(options.selected==true){
                                    $scope.questionobject={
                                        "Type":"Radio",
                                        "dynamicid":"",
                                        "questionid":"",
                                        "option":options
                                    }
                                    var pdindexvalue= pdindex.toString();
                                    var sdindexvalue= sdindex.toString();
                                    var questionindexvalue= questionindex.toString();
                                    var metricindexvalue= metricindex.toString();
                                    $scope.questionobject.questionid=pdindexvalue+sdindexvalue+questionindexvalue+metricindexvalue;
                                    var optionsindexvalue= optionsindex.toString();
                                    $scope.questionobject.dynamicid=pdindexvalue+sdindexvalue+questionindexvalue+metricindexvalue+optionsindexvalue
                                    $scope.idList.push($scope.questionobject)
                                }
                            });
                        });
                   });
                });
             });
                if($scope.idList.length===$scope.totalMetricsintheCurrentPage){
                 $scope.isQuestionAnswered=true
             }
             $timeout( function(){  angular.forEach($scope.idList, function(id, pdindex){
                 $("#" + id.dynamicid).prop("checked", true);
             }) }, 100);
         }, 
         function(response){
            alertify.error("page question failure");
        });
        }, 
        function(pagedetailsresponse){
            alertify.error("page summary failure");
        });
        }, 
        function(questionsummaryresponse){
        	alertify.error("tool summary failure");
        });
        }
// Method to get the successive page questions
        $scope.getNextPagequestiondata=function(){
            $http({
                url: "http://192.168.0.13:9090/TLG/SavePage",
                method: "POST",
                data: $scope.questionlist,
                headers: {'Content-Type':'application/json','Accept':'application/json'}
            }).then(function(response) {
                $scope.data.request.pageid=$scope.pageListIds[$scope.currentPageNo].pgid;
                if($scope.currentPageNo<$scope.totalNumberofPages){
                    $scope.currentPageNo=$scope.currentPageNo+1
                    if($scope.currentPageNo===$scope.totalNumberofPages){
                        $scope.isLastPage=true
                        $scope.showPreviousButton=true
                    }else{
                        $scope.showPreviousButton=true
                    }
                }
                $scope.idList=[]
                $http({
                    method: "Post",
                    url: "http://192.168.0.13:9090/TLG/GetQuestionnaireDetails",
                // url: "http://localhost:8080/TLG/GetQuestionnaireDetails",
                data:$scope.data,
                headers: {'Content-Type':'application/json','Accept':'application/json'}
            }).then(function(pagedetailsresponse) {
            // $scope.currentPageNo=parseInt(pagedetailsresponse.data.Page.pgno);
            $scope.currentPageID=pagedetailsresponse.data.Page.pgid;
            $scope.title=pagedetailsresponse.data.Page.commonInfo.title;
            $scope.instruction=pagedetailsresponse.data.Page.commonInfo.inst;
            $scope.Rmrks=pagedetailsresponse.data.Page.commonInfo.Rmrks;
            $http({
                method: "Post",
                url: "http://192.168.0.13:9090/TLG/GetPageDetails",
                // url: "http://localhost:8080/TLG/GetPageDetails",
                data:$scope.data,
                headers: {'Content-Type':'application/json','Accept':'application/json'}
            }).then(function(response) {
                $scope.noQuestionsintheCurrentPage=parseInt(response.data.keys.no_of_questions);
                $scope.questionlist = response.data;
                $scope.totalMetricsintheCurrentPage=0
                angular.forEach($scope.questionlist.PageQuestions, function(pd, pdindex){
                    angular.forEach(pd.sdlist, function(sd, sdindex){
                        angular.forEach(sd.qlist, function(question, questionindex){
                            angular.forEach(question.metrics, function(metric, metricindex){
                                $scope.totalMetricsintheCurrentPage=$scope.totalMetricsintheCurrentPage+1
                                angular.forEach(metric.Optlbl, function(options, optionsindex){
                                    if(options.selected==true){
                                        $scope.questionobject={
                                            "Type":"Radio",
                                            "dynamicid":"",
                                            "questionid":"",
                                            "option":options
                                        }
                                        var pdindexvalue= pdindex.toString();
                                        var sdindexvalue= sdindex.toString();
                                        var questionindexvalue= questionindex.toString();
                                        var metricindexvalue= metricindex.toString();
                                        $scope.questionobject.questionid=pdindexvalue+sdindexvalue+questionindexvalue+metricindexvalue;
                                        var optionsindexvalue= optionsindex.toString();
                                        $scope.questionobject.dynamicid=pdindexvalue+sdindexvalue+questionindexvalue+metricindexvalue+optionsindexvalue
                                        $scope.idList.push($scope.questionobject)
                                    }
                                });
                            });
                        });
                    });
                });
                $scope.isQuestionAnswered=false
                if($scope.idList.length===$scope.totalMetricsintheCurrentPage){
                    $scope.isQuestionAnswered=true
                }
                $timeout( function(){  angular.forEach($scope.idList, function(id, pdindex){
                    $("#" + id.dynamicid).prop("checked", true);
                }) }, 100);
            }, 
         function(response){
            alertify.error("page question failure");
        });
        }, 
         function(pagedetailsresponse){
             alertify.error("page summary failure");
        });
        }, 
        function(response){
            alertify.error("Save Page failure");
        });
        }

          // Method to submit the tool after user all the questions
        $scope.submitTool=function(){
            $http({
                url: "http://192.168.0.13:9090/TLG/SavePage",
                method: "POST",
                data: $scope.questionlist,
                headers: {'Content-Type':'application/json','Accept':'application/json'}
            }).then(function(response) {
                $http({
                    method: "Post",
                    url: "http://192.168.0.13:9090/TLG/SubmitQuestionnaire",
                // url: "http://localhost:8080/TLG/GetQuestionnaireDetails",
                data:$scope.data,
                headers: {'Content-Type':'application/json','Accept':'application/json'}

            }).then(function(response) {
                $window.location.href ='#/dashboard'
                alertify.success($scope.labels_sst.sst_submit_success_msg);
            }, 
        function(response){
            alertify.error("submit failure");
        });
        }, 
        function(response){
            alertify.error("Save Page failure");
        });
        }

        // Method to save the tool as draft
        $scope.draftTool=function(){
            $http({
                url: "http://192.168.0.13:9090/TLG/SavePage",
                method: "POST",
                data: $scope.questionlist,
                headers: {'Content-Type':'application/json','Accept':'application/json'}
            }).then(function(response) {
               $window.location.href ='#/dashboard'
                alertify.success($scope.labels_sst.sst_draft_success_msg);
           }, 
           function(response){
            alertify.error("Save Page failure");
        });
        }
// Method to get the previous page questions
        $scope.getPreviousPagequestiondata=function(){
        	angular.forEach($scope.pageListIds, function(pageid,index){
        		if($scope.currentPageNo===pageid.pgno){
        			var index=index-1
        			$scope.data.request.pageid=$scope.pageListIds[index].pgid;
        		}
        	});

        	if($scope.currentPageNo<=$scope.totalNumberofPages){
        		$scope.currentPageNo=$scope.currentPageNo-1
        		if($scope.currentPageNo===1){
        			$scope.isLastPage=false
        			$scope.showPreviousButton=false
        		}else{
        			$scope.showPreviousButton=true
        			$scope.isLastPage=false
        		}
        	}
         $scope.idList=[]
         $http({
            method: "Post",
            url: "http://192.168.0.13:9090/TLG/GetQuestionnaireDetails",
        		// url: "http://localhost:8080/TLG/GetQuestionnaireDetails",
        		data:$scope.data,
        		headers: {'Content-Type':'application/json','Accept':'application/json'}

        	}).then(function(pagedetailsresponse) {
        		$scope.currentPageNo=parseInt(pagedetailsresponse.data.Page.pgno);
        		$scope.currentPageID=pagedetailsresponse.data.Page.pgid;
        		$scope.title=pagedetailsresponse.data.Page.commonInfo.title;
        		$scope.instruction=pagedetailsresponse.data.Page.commonInfo.inst;
        		$scope.Rmrks=pagedetailsresponse.data.Page.commonInfo.Rmrks;
        		$http({
        			method: "Post",
        			url: "http://192.168.0.13:9090/TLG/GetPageDetails",
        		// url: "http://localhost:8080/TLG/GetPageDetails",
        		data:$scope.data,
        		headers: {'Content-Type':'application/json','Accept':'application/json'}

        	}).then(function(response) {
        		$scope.questionlist = response.data;
                $scope.noQuestionsintheCurrentPage=parseInt(response.data.keys.no_of_questions);
                $scope.totalMetricsintheCurrentPage=0
                angular.forEach($scope.questionlist.PageQuestions, function(pd, pdindex){
                 angular.forEach(pd.sdlist, function(sd, sdindex){
                    angular.forEach(sd.qlist, function(question, questionindex){
                             angular.forEach(question.metrics, function(metric, metricindex){
                                $scope.totalMetricsintheCurrentPage=$scope.totalMetricsintheCurrentPage+1
                            angular.forEach(metric.Optlbl, function(options, optionsindex){
                                if(options.selected==true){
                                    $scope.questionobject={
                                        "Type":"Radio",
                                        "dynamicid":"",
                                        "questionid":"",
                                        "option":options
                                    }
                                    var pdindexvalue= pdindex.toString();
                                    var sdindexvalue= sdindex.toString();
                                    var questionindexvalue= questionindex.toString();
                                    var metricindexvalue= metricindex.toString();
                                    $scope.questionobject.questionid=pdindexvalue+sdindexvalue+questionindexvalue+metricindexvalue;
                                    var optionsindexvalue= optionsindex.toString();
                                    $scope.questionobject.dynamicid=pdindexvalue+sdindexvalue+questionindexvalue+metricindexvalue+optionsindexvalue
                                    $scope.idList.push($scope.questionobject)
                                }
                            });
                        });
                   });
                });
             });
                $scope.isQuestionAnswered=false
                if($scope.idList.length===$scope.totalMetricsintheCurrentPage){
                 $scope.isQuestionAnswered=true
             }
             $timeout( function(){  angular.forEach($scope.idList, function(id, pdindex){
                 $("#" + id.dynamicid).prop("checked", true);
             }) }, 100);
         }, 
         function(response){
            alertify.error("page question failure");
        });
        }, 
         function(pagedetailsresponse){
             alertify.error("page summary failure");
        });

        }
        $scope.getquestiondata()
        $scope.initialize();
    }]);