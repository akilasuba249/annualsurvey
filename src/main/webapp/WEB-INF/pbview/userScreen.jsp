    <%@ include file = "header.jsp" %>
     
      <section class="survey blue-patch">
         <div class="container">
            <div class="row">
               <div class="col-md-1">
               </div>
               <div class="col-12 col-md-10">
               
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
         <div class="container">
            <div class="row">
               <div class="col-md-1">
               </div>
               <div class="col-12 col-md-12 col-lg-10">
                  <div class="survey-letter">
                     <div class="row">
                        <div class="col-12">
                           <div class="letter-box">
                              <h3>Group HR Annual Survey 2020</h3>
                              <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.</p>
                            
                           
                           <div class="title-border">
                               <p class="middle-text">It's mandatory to complete/update your profile to submit this survey</p>
                           </div>
                              <!-- <div class="row">
                                 <div class="col-3">
                                   <div class="single-line"></div>
                                 </div>
                                 <div class="col-6"> <p class="middle-text">It's mandatory to complete/update your profile to submit this survey</p></div>
                                 <div class="col-3">
                                   <div class="single-line"></div>
                                 </div>
                                 </div> -->
                              <!-- <form class="login-form input-style1" action="demograpic" method="post" id="demograpic"> -->
                                  
                                  <form:form cssClass="login-form input-style1" action="demograpic" 
                                  method="POST" id="demograpic" modelAttribute="EmployeeDTO">
                                  
                                  <input type="hidden" id="pkEmp1" name="pkEmp" value="${pkEmp}">				 
         						  <input type="hidden" id="Employeeid" name="empId" value="${empId}">
         						  
                                 <div class="row">
                                    <div class="col-md-3">
                                       <div class="avatar-wrapper">
                                          <img class="profile-pic" src="assets/images/female-placeholder.png" />
                                          <div class="upload-button">
                                             <i class="fa fa-arrow-circle-up" aria-hidden="true"></i>
                                          </div>
                                          <input class="file-upload" type="file" accept="image/*" />
                                       </div>
                                    </div>
                                    <div class="col-md-9">
                                       <div class="row">
                                          <div class="col-md-6">
                                             <div class="form-group">
                                                <label>first name</label>
                                                <!-- <input type="text" name="name"n class="form-control" placeholder="Tejal" autocomplete="off"> -->
                                                 <input type="text" id="fname" name="fName"n class="form-control must name" value="${employee.fName}" 
                                                 placeholder="first name" autocomplete="off">
                                             </div>
                                          </div>
                                          <div class="col-md-6">
                                             <div class="form-group">
                                                <label>last name</label>
                                                <!-- <input type="text" name="name"n class="form-control" placeholder="Sawant" autocomplete="off"> -->
                                                 <input type="text" id="lname" name="lName"n class="form-control must name" value="${employee.lName}" 
                                                 placeholder="last name" autocomplete="off">
                                             </div>
                                          </div>
                                          <div class="col-md-6">
                                             <div class="gender-box">
                                                <div class="switch-field ">
                                                   <input type="radio" id="male" name="gender" value="Male"/>
                                                   <label for="male">Male</label>
                                                   <input type="radio" id="female" name="gender" value="Female" />
                                                   <label for="female">Female</label>
                                                </div>
                                             </div>
                                          </div>
                                          <div class="col-md-6">
                                             <div class="form-group">
                                                <label>date of Birth</label>
                                                <input type="text" name="dateOfBirth"n class="form-control  must datepicker" 
                                                id="datepicker3" placeholder="DD/MM/YYYY" value="${employee.dateOfBirth}" autocomplete="off">
                                             </div>
                                          </div>
                                          <div class="col-md-6">
                                             <div class="form-group">
                                                <label>Email ID</label>
                                                <input type="text" id="emailId" name="emailId"n class="form-control" 
                                                placeholder="email id" value="${employee.emailId}" autocomplete="off">
                                             </div>
                                          </div>
                                          <div class="col-md-6">
                                             <div class="form-group">
                                             <input type="hidden" id="countryid"  value="${employee.country}">
             								  <input type="hidden" id="genderid"  value="${employee.gender}"> 
                                                <label for="">Country</label>
                                                 <div class="drop-down">
                                                  <!--  <div class="selected">
                                                      <a href="javascript:void(0);"><span>India</span></a>
                                                      <i class="fa fa-angle-down" aria-hidden="true"></i>
                                                   </div> -->
                                                   <div class="options">
                                                   <select id="country2" class="form-control" name ="country" required="true">
                                                  </select>
                                                   </div> 
                                                </div> 
                                             </div>
                                          </div>
                                          <div class="col-md-6">
                                             <div class="form-group">
                                                <label for="">Sector</label>
                                                <div class="drop-down"> 
                                                    <!-- <div class="selected">
                                                      <a href="javascript:void(0);"><span>Aerospace & defence</span></a>
                                                      <i class="fa fa-angle-down" aria-hidden="true"></i>
                                                   </div> -->
                                                   <div class="options">
                                                      <!-- <ul>
                                                         <li><a href="javascript:void(0);">Aerospace & defence<span class="value">1</span></a></li>
                                                         <li><a href="javascript:void(0);">Aerospace & defence<span class="value">2</span></a></li>
                                                         <li><a href="javascript:void(0);">Aerospace & defence<span class="value">3</span></a></li>
                                                      </ul> -->
                                                <select  class="form-control must" id="sector" name="sector" >
									            <option value="">Select Sector</option>
									        	<c:forEach items="${sector}" var="sector">
												<option value="${sector.sectorId}">${sector.sectorName}</option>
												</c:forEach>
									        	</select> 
                                                   </div>  
                                                 </div> 
                                             </div>
                                          </div>
                                          <div class="col-md-6">
                                             <div class="form-group">
                                                <label for="">Business</label>
                                                <div class="drop-down">
                                                   <!-- <div class="selected">
                                                      <a href="javascript:void(0);"><span>Dimension Title</span></a>
                                                      <i class="fa fa-angle-down" aria-hidden="true"></i>
                                                   </div> -->
                                                   <div class="options">
                                                      <select id="business" name="business"  class="form-control must">
											            <option value="">Select Business</option>      	
											         	<c:forEach items="${business}" var="business">
														<option value="${business.businessId}">${business.businessName}</option>
														</c:forEach>
											          </select>
                                                      <!-- <ul>
                                                         <li><a href="javascript:void(0);">Aerospace<span class="value">1</span></a></li>
                                                         <li><a href="javascript:void(0);">Aerospace<span class="value">2</span></a></li>
                                                         <li><a href="javascript:void(0);">Aerospace<span class="value">3</span></a></li>
                                                      </ul> -->
                                                   </div>
                                                </div>
                                             </div>
                                          </div>
                                       </div>
                                    </div>
                                 </div>
                              </form:form>

                              <div class="btn-groups ">
                      <!-- <a href="javascript:void(0);" class="survey-btn" data-toggle="modal" data-target="#update-profile">Update Profile</a> -->
                      <a href="javascript:void(0);" class="survey-btn" data-toggle="modal"  onclick="saveUser()">Update Profile</a>
                     <!--  <a href="javascript:void(0);" class="survey-btn" data-toggle="modal" 
                      id="saveuser" onclick="saveUser()">Update Profile</a> -->
               </div>
                          
                           </div>
                        </div>
                     </div>
                     <button type="button" class="information-details" data-toggle="modal" data-target="#exampleModal">
                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 20 20">
                           <path id="ic_info_outline_24px" d="M11,17h2V11H11ZM12,2A10,10,0,1,0,22,12,10,10,0,0,0,12,2Zm0,18a8,8,0,1,1,8-8A8.011,8.011,0,0,1,12,20ZM11,9h2V7H11Z" transform="translate(-2 -2)" fill="#fff"/>
                        </svg>
                     </button>
                     <!-- Modal -->
                     <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 14 14">
                                       <defs>
                                          <style>.a{fill:#fff;}</style>
                                       </defs>
                                       <path class="a" d="M19,6.41,17.59,5,12,10.59,6.41,5,5,6.41,10.59,12,5,17.59,6.41,19,12,13.41,17.59,19,19,17.59,13.41,12Z" transform="translate(-5 -5)"/>
                                    </svg>
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

                      <div class="modal" id="update-profile" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                           <div class="modal-content">
                              
                              <div class="modal-body">
                                 <img class="bg-img" src="assets/images/modal-image.png">
                                 <div class="profile-div-img">
                                    <img  class="profile-cir-img" src="assets/images/female-placeholder.png">
                                    <img src="assets/images/img-top.png">
                                 </div>
                                 <h1>Hey <%=session.getAttribute("username")%>,</h1>
                                 <h4>Your Profile is updated. You can start the Survey</h4>
                               <%-- <spring:url value="/getSurveyDtls" var="surveyurl" htmlEscape="true"/>
 								<a href="${surveyurl}" class="survey-btn">Start Survey</a> --%>
 								<%-- <spring:url value="/getSurveyDtls" var="surveyurl" htmlEscape="true"/> --%>
 								<a href="getSurveyDtls" class="survey-btn">Start Survey</a>
                              </div>
                           </div>
                        </div>
                     </div>

                     <div class="document-img">
                        <img src="assets/images/document.svg">
                     </div>
                     <div class="man-img">
                        <img src="assets/images/man-img.svg">
                     </div>
                  </div>
               </div>
               <div class="col-md-1">
               </div>
            </div>
         </div>
      </section>
     
         <%@ include file = "footer.jsp" %>