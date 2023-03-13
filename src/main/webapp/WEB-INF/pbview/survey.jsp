
  <%@ include file = "header.jsp" %>

<section class="survey survey-main-page">
   <div class="container">
      <div class="row">
         <div class="col-md-1">
         </div>
         <div class="col-12 col-md-10">
         <!--    <div class="welcome-div-wrap">
               <h3>Welcome</h3>
               <h1 class="into-heading">Tejal Sawant</h1>
               <p>please complete Group HR Annual Survey before 27th Apr, 2020</p>
            </div> -->
            
             <c:set var="user" value='<%=session.getAttribute("emailId")%>'></c:set>
			   
			    <c:if test="${sapFlag =='N'}">
				 <div class="welcome-div-wrap">
                     <h3>Welcome</h3>
                     <h1 class="into-heading"><%=session.getAttribute("username")%></h1>
                     <p>please complete Group HR Annual Survey before 27th Apr, 2020</p>
                  </div>
			    </c:if>
			    <c:if test="${sapFlag =='Y'}">
				 <div class="welcome-div-wrap">
                     <h3>Welcome</h3>
                     <h1 class="into-heading"><%=session.getAttribute("username")%></h1>
                     <p>please complete Group HR Annual Survey before 27th Apr, 2020</p>
                  </div>
			    </c:if>
         </div>
         <div class="col-md-1">
         </div>
      </div>
   </div>
</section>
<section id="StartSurvey" class="StartSurvey-section">
<div class="main-wrap position-relative survey-main-page">
<form:form  action="responseAdd" id="responseStart"  modelAttribute="MTResponse">
   <div class="container-fluid padd-zero">
      <div class="scroll_horizontal">
         <ul class="survey_heading_div survey-top-heading">
            <h4 class="cout_span d-block d-lg-none"><span></span>/<i></i></h4>
             <%int tabid=0; %>
                           <c:forEach items="${areas}" var="role" varStatus="status">
                           	 <%tabid++;%>
                           <li class="active" id="li_<%=tabid%>" data-goto="content_<%=tabid%>" data-index="<%=tabid%>">
                            
                              <i class="d-none"></i>
                              <div class="text_div">
                                 <!-- <h4>MeCentral Support</h4> -->
                                 <h4>${role.areaDesc}</h4>
                                 
                              </div>
                              <div class="progress">
  								<div id="progressid" class="progress-bar"  role="progressbar" style="width: 0%"  aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
								<input type="hidden" class="progressbarwidth" id="progresshidid" name="progressbarwidth" >
								</div>
                           </li>
                           </c:forEach>
           
            
         </ul>
      </div>
   </div>
   <div class="container">
      <div class="row">
         <div class="col-md-1">
         </div>
         <div class="col-12 col-md-10" id="left_pad_mar">
            <div class="survey-letter center_div_MP">
               <div class="row">
                  <div class="col-12">
                     <div class="letter-box">
                        <div class="slider">
                           <div class="survey-qtn-div">
                           <%int radioCnt =0; int x=1;  int contid=0; %> 
 							<input type="hidden" id="surveyId" name="surveyId" value="${surveyId}">			
							 <input type="hidden" id="Empid" name="Empid" value="${empId}">
							 <input type="hidden" id="pkEmp" name="pkEmp" value="${pkEmp}">				 
						     <input type="hidden" id="year" name="year">				 
						     <!-- <input type="hidden" id="operation" name="operation" value="N"> -->
						     <input type="hidden" id="operation" name="operation" value="${operation}">				 
						     <input type="hidden" id="responsemap" name="responsemap" value="">				 
							 <c:set var="iter" value="0" />
							 
 							<c:forEach items="${areas}" var="role" varStatus="status">
 							 <%radioCnt++;%>
 							  <%contid++;%>
 							  <%-- <h4>${role.areaDesc}</h4> --%>
                               <div class="inner_content_div" id="content_<%=contid%>" data-li="li_<%=contid%>">
                                 <ul class="question_ul_inner">
                                 	<%int a = 0;String b;int c = 65;int d=0;int tempC=0; %> 
                                 	<!-- temporary solution -->
                                  <c:forEach items="${areaquestion}" var="role1" varStatus="status1">
                                  <c:if test="${role1.questionArea.areaDesc==role.areaDesc}" > <%++tempC;%></c:if>
                                  </c:forEach>
                                  
                                     <c:forEach items="${areaquestion}" var="role1" varStatus="status1">
                                       <c:if test="${role1.questionArea.areaDesc==role.areaDesc}" > 
					           			<%++a;%>
						          		 <% 
						          		 if (a % 2 == 0) {
												  b="gray";
												 } else { 
												  //b="white";
												  b="yellow";
												 }  
						          			 pageContext.setAttribute("b", b);
				 						%>
     
     							 <c:if test="${role1.questType.pkQuestType=='1'}">
                                     <%-- <li class="type1 active main-que <c:out value="${b}"/> <% if (tempC == a) { %>last_click<% } %>" id="Resp${role1.questionId} "> --%>
                                     <li class="type1 main-que <c:out value="${b}"/>" id="Resp${role1.questionId} ">
                                    <input type="hidden" class="dvComments${role.areaCode}Resp" name="tblresponsedtls[${status1.index}].comments" >
							        <input type="hidden" name="tblresponsedtls[${status1.index}].questionArea.areaCode" value="${role.areaCode}">
							        <input type="hidden" name="tblresponsedtls[${status1.index}].questionBankId" value="${role1.questionId}">
							        <input type="hidden" name="tblresponsedtls[${status1.index}].questionBankId" value="${role1.questionId}">
                                       <p><span><%=a %>. &nbsp;</span>${role1.questionDesc}</p>
                                    	
                                        <div class="switch-field">
    									<input type="hidden" name="tblresponsedtls[${status1.index}].questionType" value="Rate">
                                         
                                          <input type="radio" class="radio-<%=radioCnt%> radio-custom" id="Resp${role1.questionId}rad1" name="tblresponsedtls[${status1.index}].answerNo" value="1">
                                          <label for="Resp${role1.questionId}rad1" class="border-right- "  >Strongly Disagree</label>
                                         <%++radioCnt;%>
                                          <input type="radio" class="radio-<%=radioCnt%> radio-custom" id="Resp${role1.questionId}rad2" name="tblresponsedtls[${status1.index}].answerNo" value="2">
                                          <label for="Resp${role1.questionId}rad2" class="border-right-0 ">Disagree</label>                        
                                          <%++radioCnt;%>
                                          <input type="radio" class="radio-<%=radioCnt%> radio-custom" id="Resp${role1.questionId}rad3" name="tblresponsedtls[${status1.index}].answerNo" value="3">
                                          <label for="Resp${role1.questionId}rad3" class="border-right-0 ">Neutral</label>
                                          <%++radioCnt;%>
                                          <input type="radio" class="radio-<%=radioCnt%> radio-custom" id="Resp${role1.questionId}rad4" name="tblresponsedtls[${status1.index}].answerNo" value="4">
                                          <label for="Resp${role1.questionId}rad4" class="border-right-0 ">agree</label>
                                          <%++radioCnt;%>
                                          <input type="radio" class="radio-<%=radioCnt%> radio-custom" id="Resp${role1.questionId}rad5"  name="tblresponsedtls[${status1.index}].answerNo" value="5">
                                          <label for="Resp${role1.questionId}rad5" class="border-right-0 ">Strongly Agree</label>
                                          <%++radioCnt;%>
                                          <input type="radio" class="radio-<%=radioCnt%> radio-custom" id="Resp${role1.questionId}rad0" name="tblresponsedtls[${status1.index}].answerNo" value="0">
                                          <label for="Resp${role1.questionId}rad0">N/A</label>
                                            <input type="radio"  name="tblresponsedtls[${status1.index}].answerNo" value="-1" checked="checked" class="defaultRadio">
                                       </div>  
                                       </li>
                                        </c:if>
                                        
                                         <c:if test="${role1.questType.pkQuestType=='2'}"> 
                                         
                                        <%--  <input type="hidden" class="dvComments${role.areaCode}Resp" name="tblresponsedtls[${status1.index}].comments" >
       									 <input type="hidden" name="tblresponsedtls[${status1.index}].questionArea.areaCode" value="${role.areaCode}">
        								 <input type="hidden" name="tblresponsedtls[${status1.index}].questionBankId" value="${role1.questionId}">
                                     	 <input type="hidden" name="tblresponsedtls[${status1.index}].questionBankId" value="${role1.questionId}">
                                         
                                         <li class="type2 active main-que <c:out value="${b}"/>" id="Resp${role1.questionId} ">         
									      <p><span><%=a %>. &nbsp;</span>${role1.questionDesc}</p>
									      <div class="question-type-2 add-comment mt-5">
									      <input type="hidden" name="tblresponsedtls[${status1.index}].questionType" value="OpenEnded">
                                          
                                          <textarea class="form-control must radio-custom1" id="Resp${role1.questionId}text" 
                                          name="tblresponsedtls[${status1.index}].answerText" maxlength="1000"></textarea>
                                          
                                          
                                          <textarea class="form-control must radio-custom1" id="Resp${role1.questionId}text" 
                                          name="tblresponsedtls[${status1.index}].answerText" maxlength="1000"
                                          placeholder="This a very nice initiative taken by HR department"></textarea>
                                          
                                          <a class="skip " style="display: none;">skip</a>
                                          <button type="button" class=" ok-btn" style="display: inline-block;">Ok</button>
                                         </div>
                                         </li> --%>
                                          <li class="type2  main-que <c:out value="${b}"/>" id="Resp${role1.questionId} ">   
                                           <input type="hidden" class="dvComments${role.areaCode}Resp" name="tblresponsedtls[${status1.index}].comments" >      
									      <input type="hidden" name="tblresponsedtls[${status1.index}].questionArea.areaCode" value="${role.areaCode}">
        								  <input type="hidden" name="tblresponsedtls[${status1.index}].questionBankId" value="${role1.questionId}"> 
									      <p><span><%=a %>. &nbsp;</span>${role1.questionDesc}</p>
									      <div class="question-type-2 add-comment mt-5">
									      <input type="hidden" name="tblresponsedtls[${status1.index}].questionType" value="OpenEnded">
                                          
                                          <%-- <textarea class="form-control must radio-custom1" id="Resp${role1.questionId}text" 
                                          name="tblresponsedtls[${status1.index}].answerText" maxlength="1000"></textarea>
                                           --%>
                                          
                                          <textarea class="form-control must radio-custom1" id="Resp${role1.questionId}text" 
                                          name="tblresponsedtls[${status1.index}].answerText" maxlength="1000"
                                          placeholder="Add comments"></textarea>
                                          
                                          <a class="skip " style="display: none;">skip</a>
                                          <button type="button" class=" ok-btn" style="display: inline-block;">Ok</button>
                                         </div>
                                         </li>
        								</c:if>  
                                    
                                     	 <c:if test="${role1.questType.pkQuestType=='3'}"> 
                                     	 <li class="type3  main-que sub-que <c:out value="${b}"/>" id="Resp${role1.questionId} ">         
	            						 
	            						<%--  <input type="hidden" class="dvComments${role.areaCode}Resp" name="tblresponsedtls[${status1.index}].comments" > --%>
	            						  <input type="hidden" class="dvComments${role.areaCode}Resp" name="tblresponsedtls[${status1.index}].comments" >
									     <input type="hidden" name="tblresponsedtls[${status1.index}].questionArea.areaCode" value="${role.areaCode}">
									     <input type="hidden" name="tblresponsedtls[${status1.index}].questionBankId" value="${role1.questionId}">
                                     <%-- 	 <input type="hidden" name="tblresponsedtls[${status1.index}].questionBankId" value="${role1.questionId}"> --%>
	            						 
	            						 <p><span><%=a %>. &nbsp;</span>${role1.questionDesc}</p>
	            						 <input type="hidden" name="tblresponsedtls[${status1.index}].questionType" value="Rate">
	            						 <ul class="switch-field question-type-3">
                                          <li>
                                            
                                             <input type="radio" id="Resp${role1.questionId}rad1" name="tblresponsedtls[${status1.index}].answerNo" value="1">
                                             <label for="Resp${role1.questionId}rad1" class="radio-custom" >1</label>
                                             
                                              <input type="radio" id="Resp${role1.questionId}rad2" name="tblresponsedtls[${status1.index}].answerNo" value="2">
                                             <label for="Resp${role1.questionId}rad2" class="radio-custom">2</label>
                                             
                                             <input type="radio" id="Resp${role1.questionId}rad3" name="tblresponsedtls[${status1.index}].answerNo" value="3">
                                             <label for="Resp${role1.questionId}rad3" class="radio-custom">3</label>
                                             
                                             <input type="radio" id="Resp${role1.questionId}rad4" name="tblresponsedtls[${status1.index}].answerNo" value="4">
                                             <label for="Resp${role1.questionId}rad4" class="radio-custom">4</label>
                                             
                                             <input type="radio" id="Resp${role1.questionId}rad5"  name="tblresponsedtls[${status1.index}].answerNo" value="5">
                                             <label for="Resp${role1.questionId}rad5" class="radio-custom">5</label>
                                             
                                             <input type="radio" id="Resp${role1.questionId}rad0" name="tblresponsedtls[${status1.index}].answerNo" value="0">
                                             <label for="Resp${role1.questionId}rad0" class="radio-custom">NA</label>
                                             
                                             <div class="col-sm-2 col-xs-2 text-center radio-custom">
								             <input type="radio"  name="tblresponsedtls[${status1.index}].answerNo" value="-1"  checked="checked" class="defaultRadio">
								             </div>
                                          </li>
	            						</ul>
	            						</li>
             							 </c:if>  
             							 
    
            							<c:if test="${role1.questType.pkQuestType=='4'}">
            							<li class="type4  main-que sub-que <c:out value="${b}"/>" id="Resp${role1.questionId} ">
                  						
                  						<p><span><%=a %>. &nbsp;</span>${role1.questionDesc}</p>
                  						<input type="hidden" name="tblresponsedtls[${status1.index}].questionType" value="Subquestion">
                  						 <input type="hidden" class="dvComments${role.areaCode}Resp" name="tblresponsedtls[${status1.index}].comments" >
								          
                  						  <ul class="switch-field question-type-3 question-type-4">
                  						  <c:forEach items="${role1.mtsubquestion}" var="role2" varStatus="status2">
                  						   
                                          <li>
                                          
                                          <input type="hidden" name="tblresponsedtls[${status1.index}].questionArea.areaCode" value="${role.areaCode}">
        								  <input type="hidden" name="tblresponsedtls[${status1.index}].questionBankId" value="${role1.questionId}">
                                        <%--   <input type="hidden" name="tblresponsedtls[${status1.index}].questionBankId" value="${role1.questionId}"> --%>
                                          <input type="hidden" name="tblresponsedtls[${status1.index}].mtrespsub[${status2.index}].subquestionId" value="${role2.subQuestionId}">         
								          <input type="hidden" name="tblresponsedtls[${status1.index}].mtrespsub[${status2.index}].questionBankId" value="${role1.questionId}">
                                          
                                             <div class="col-sm-12 ">
									       <p class="subq"><%=(char)c++ %>. ${role2.subQuestDesc}</p>
									       </div>
									       
                  						  <div class="switch-field">
                                          <input type="radio" id="Resp${role2.subQuestionId}subquest1" name="tblresponsedtls[${status1.index}].mtrespsub[${status2.index}].answerNo" value="1">
                                          <label for="Resp${role2.subQuestionId}subquest1" class="border-right-0 radio-custom"  >Very Low</label>
                                         <%++radioCnt;%>
                                          <input type="radio" id="Resp${role2.subQuestionId}subquest2" name="tblresponsedtls[${status1.index}].mtrespsub[${status2.index}].answerNo" value="2">
                                          <label for="Resp${role2.subQuestionId}subquest2" class="border-right-0 radio-custom">Low</label>                        
                                          <%++radioCnt;%>
                                         
                                          <input type="radio" id="Resp${role2.subQuestionId}subquest3" name="tblresponsedtls[${status1.index}].mtrespsub[${status2.index}].answerNo" value="3">
                                          <label for="Resp${role2.subQuestionId}subquest3" class="border-right-0 radio-custom">Neutral</label>
                                          <%++radioCnt;%>
                                         
                                          <input type="radio" id="Resp${role2.subQuestionId}subquest4" name="tblresponsedtls[${status1.index}].mtrespsub[${status2.index}].answerNo" value="4">
                                          <label for="Resp${role2.subQuestionId}subquest4" class="border-right-0 radio-custom">High</label>
                                          <%++radioCnt;%>
                                         
                                          <input type="radio" id="Resp${role2.subQuestionId}subquest5"  name="tblresponsedtls[${status1.index}].mtrespsub[${status2.index}].answerNo" value="5">
                                          <label for="Resp${role2.subQuestionId}subquest5" class="border-right-0 radio-custom">Very High</label>
                                          <%++radioCnt;%>
                                         
                                          <input type="radio" id="Resp${role2.subQuestionId}subquest0" name="tblresponsedtls[${status1.index}].mtrespsub[${status2.index}].answerNo" value="0">
                                          <label for="Resp${role2.subQuestionId}subquest0" class="radio-custom">N/A</label>
                                       </div>
                                        <div class="col-sm-2 col-xs-2 text-center radio-custom" style="display:none">
						               <input type="radio" name="tblresponsedtls[${status1.index}].mtrespsub[${status2.index}].answerNo" value="-1" checked="checked"  class="defaultRadio">
						               </div>
                                          </li>
						               
                                       </c:forEach>
                                         
                                       </ul>
                  						  
                                        
                                       </li>          
               						  </c:if>  
										
										 <c:if test="${role1.questType.pkQuestType=='5'}"> 
										<li class="type5  main-que <c:out value="${b}"/>" id="Resp${role1.questionId} ">
										<input type="hidden" class="dvComments${role.areaCode}Resp" name="tblresponsedtls[${status1.index}].comments" >
								        <input type="hidden" name="tblresponsedtls[${status1.index}].questionArea.areaCode" value="${role.areaCode}">
								        <input type="hidden" name="tblresponsedtls[${status1.index}].questionBankId" value="${role1.questionId}">
                                       <%--  <input type="hidden" name="tblresponsedtls[${status1.index}].questionBankId" value="${role1.questionId}"> --%>
										<p><span><%=a %>. &nbsp;</span>${role1.questionDesc}</p>
										<div class="switch-field yes-no">
										 <input type="hidden" name="tblresponsedtls[${status1.index}].questionType" value="Rate">
                                          
                                          <div class="radio-custom optcustomradip">
                                          <input type="radio" id="Resp${role1.questionId}rad1" name="tblresponsedtls[${status1.index}].answerNo" value="1">
                                          <label for="radio-61" class="border-right-0 radio-custom">Yes </label>
                                          </div>
                                          
                                          <div class="radio-custom optcustomradip">
                                          <input type="radio" id="Resp${role1.questionId}rad2" name="tblresponsedtls[${status1.index}].answerNo" value="2">
                                          <label for="radio-62" class="radio-custom">No</label>                        
                                          </div>
                                         <%-- <div class="col-sm-12 text-center radio-custom">
							              <input type="radio"  name="tblresponsedtls[${status1.index}].answerNo" value="-1" checked="checked"  class="defaultRadio" >
							            </div>   --%>  
          
                                       </div>
							                   
							            <%-- <div class="col-sm-3 text-center radio-custom optcustomradip">
							           		<input type="radio" id="Resp${role1.questionId}rad1" name="tblresponsedtls[${status1.index}].answerNo" value="1">
							           		 <label style="margin-left: 10px;">Yes</label>
							            </div>
							            <div class="col-sm-3 text-center radio-custom optcustomradip">
							              <input type="radio" id="Resp${role1.questionId}rad2" name="tblresponsedtls[${status1.index}].answerNo" value="2">
							              <label style="margin-left: 10px;">No</label>
							            </div>  --%>   
										</li>
         								 </c:if>
         								 
							              <c:if test="${role1.questType.pkQuestType=='6'}"> 
							              <li class="main-que <c:out value="${b}"/>" id="Resp${role1.questionId} ">         
							        
							        <input type="hidden" class="dvComments${role.areaCode}Resp" name="tblresponsedtls[${status1.index}].comments" >
							        <input type="hidden" name="tblresponsedtls[${status1.index}].questionArea.areaCode" value="${role.areaCode}">
							        <input type="hidden" name="tblresponsedtls[${status1.index}].questionBankId" value="${role1.questionId}">
                                    <%-- <input type="hidden" name="tblresponsedtls[${status1.index}].questionBankId" value="${role1.questionId}"> --%>
							        
							        <p><span><%=a %>. &nbsp;</span>${role1.questionDesc}</p>
							        <div class="switch-field">
							        <input type="hidden" name="tblresponsedtls[${status1.index}].questionType" value="Rate">
							              
							              
							              <input type="radio" id="Resp${role1.questionId}rad1" name="tblresponsedtls[${status1.index}].answerNo" value="1">
                                          <label for="Resp${role1.questionId}rad1" class="border-right-0 radio-custom"  >Never</label>
                                         <%++radioCnt;%>
                                         
                                         <input type="radio" id="Resp${role1.questionId}rad2" name="tblresponsedtls[${status1.index}].answerNo" value="2">
                                          <label for="Resp${role1.questionId}rad2" class="border-right-0 radio-custom">Once in 2 months</label>                        
                                          <%++radioCnt;%>
                                         
                                         <input type="radio" id="Resp${role1.questionId}rad3" name="tblresponsedtls[${status1.index}].answerNo" value="3">
                                          <label for="Resp${role1.questionId}rad3" class="border-right-0 radio-custom">Once in a month</label>
                                          <%++radioCnt;%>
                                         
                                         <input type="radio" id="Resp${role1.questionId}rad4" name="tblresponsedtls[${status1.index}].answerNo" value="4">
                                          <label for="Resp${role1.questionId}rad4" class="border-right-0 radio-custom">Once a week</label>
                                          <%++radioCnt;%>
                                         
                                         <input type="radio" id="Resp${role1.questionId}rad5" name="tblresponsedtls[${status1.index}].answerNo" value="5">
                                          <label for="Resp${role1.questionId}rad5" class="border-right-0 radio-custom">Once a day</label>
                                          <%++radioCnt;%>
                                         
                                         <input type="radio" id="Resp${role1.questionId}rad0" name="tblresponsedtls[${status1.index}].answerNo" value="0">
                                          <label for="Resp${role1.questionId}rad6" class="radio-custom">N/A</label>
                                          
                                             <div class="col-sm-2 col-xs-2 text-center radio-custom">
								              <input type="radio"  name="tblresponsedtls[${status1.index}].answerNo" value="-1" checked="checked" class="defaultRadio">
								            </div>
                                          
                                       </div>  
                                       </li>
							        </c:if>  
                                    </c:if>
                                    </c:forEach>
                                     <li class="commentli">
                                       
                                       <div class="addCommentDiv">
                                       <div class="question-type-2 add-comment">
                                          <input type="checkbox"  id="Comments${role.areaCode}" class="comment addcmntchk" >
                                          <label for="Comments${role.areaCode}">ADD Comment</label>
                                          <div class="clearfix"></div>
                                          <div class="col-sm-12 comment2 txtdiv" id="dvComments${role.areaCode}" name="" style="display: none;">
                                          <textarea type="text" class="form-control Addcomments" id="dvComments${role.areaCode}text"
       										 maxlength="1000" placeholder="Please enter your comments"></textarea>
                                           <a class="skipcomment ">skip</a>
                                          </div>
                                          <button type="button" class="ok-btn okcomment" ><span class="">Ok</span></button>
                                       </div>
                                       <div class="clearfix"></div>
                                       </div>
                                       </li>
                                 </ul>
                              </div>
                              <!-- <div class="form-group addCommentDiv">  -->
                            
                                       <%-- <div class="question-type-2 add-comment">
                                          <input type="checkbox"  id="Comments${role.areaCode}" class="comment addcmntchk" >
                                          <label for="Comments${role.areaCode}">ADD Comment</label>
                                          <div class="clearfix"></div>
                                          <div class="col-sm-12 comment2 txtdiv" id="dvComments${role.areaCode}" name="" style="display: none;">
                                          <textarea type="text" class="form-control Addcomments" id="dvComments${role.areaCode}text"
       										 maxlength="1000" placeholder="Please enter your comments"></textarea>
                                          <a class="skip ">skip</a>
                                          </div>
                                          <button type="button" class=" ok-btn" >Ok</button>
                                       </div>
                                       <div class="clearfix"></div> --%>
                             <!--  </div> -->
                            
                              </c:forEach>
                           </div>    
                        </div>
                     </div>
                  </div>
               </div>
               <div class="btn-groups survey-btns">
              <!--  <button type="button" class="addbtn btn btn-default" id="saveUser" onclick="saveUserResponse()" > Save  </button>
              <button type="button" class="addbtn btn btn-default" id="submitUser" onclick="submitUserResponse()" > Submit </button> -->
                    
                    <button type="button" class="survey-btn save-survey" id="saveUser" onclick="saveUserResponse()" > Save survey </button>
                  <button type="button" class="survey-btn submit-btn" id="submitUser" onclick="submitUserResponse()" > Submit </button>
                  
                    <!-- <a class="survey-btn save-survey " id="saveUser" onclick="saveUserResponse()">Save survey</a> -->
                 
            <%--    <spring:url value="/thankyou" var="thankyouurl" htmlEscape="true"/>
               <a href="${thankyouurl}"  class="survey-btn submit-btn" style="display: none;" id="submitUser" onclick="submitUserResponse()">Submit</a> --%>
               </div>
               <button type="button" class="information-details" data-toggle="modal" data-target="#exampleModal">
                  <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 20 20">
                     <path id="ic_info_outline_24px" d="M11,17h2V11H11ZM12,2A10,10,0,1,0,22,12,10,10,0,0,0,12,2Zm0,18a8,8,0,1,1,8-8A8.011,8.011,0,0,1,12,20ZM11,9h2V7H11Z" transform="translate(-2 -2)" fill="#fff"/>
                  </svg>
                  <span class="tooltiptext">View Instruction</span>
               </button>
               <!-- Modal -->
               <div class="modal" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                  <div class="modal-dialog" role="document">
                     <div class="modal-content">
                        <div class="modal-header">
                           <h5 class="modal-title" id="exampleModalLabel">
                              <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 20 20">
                                 <path id="ic_info_outline_24px" d="M11,17h2V11H11ZM12,2A10,10,0,1,0,22,12,10,10,0,0,0,12,2Zm0,18a8,8,0,1,1,8-8A8.011,8.011,0,0,1,12,20ZM11,9h2V7H11Z" transform="translate(-2 -2)" fill="#fff"/>
                              </svg>
                              Instructions
                           </h5>
                           <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                           <span aria-hidden="true">&times;</span>
                           </button>
                        </div>
                        <div class="modal-body">
                           <ol>
                              <li> The questions are on a Five point rating scale.</li>
                              <li>If any questions is "Not Applicable" to you, Please choose N/A</li>
                              <li>Feel free to provide qualitative comments at the end of each area, using the "Add Comment" button.</li>
                              <li>Please make sure that you click on the "SUBMIT" button at the end of the survey.</li>
                              <li>You will be allowed to "SUBMIT" only after completion of profile.</li>
                              <li>Please not that all fields and questions are mandatory.</li>
                           </ol>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="document-img">
                  <img src="assets/images/document.svg" >
               </div>
               <div class="man-img">
                  <h4 class="cout_span"><span>0</span>/<i>19</i></h4>
                  <img src="assets/images/survey-man-img.svg" >
               </div>
            </div>
         </div>
         <div class="col-md-1">
         </div>
      </div>
   </div>
   </form:form>
</div>
</section>
<div class="modal fade" id="Welcome" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" id="buttonClose">&times;</button>
           
          </div>
          <div class="modal-body">
       <!--    <img src="images/thank-you.png"/>
        -->    
        <div class="padder">
        <div class="alert alert-success">
        <span class="glyphicon glyphicon-warning-sign"></span> Thank You For Completing Survey</div>
            </div>
           </div>
        </div>
        <!-- /.modal-content --> 
      </div>
      <!-- /.modal-dialog --> 
    </div>  
 <%@ include file = "footer.jsp" %>