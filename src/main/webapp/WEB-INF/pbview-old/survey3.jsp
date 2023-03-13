<%@ include file = "header.jsp" %>

<body>
      <div class="landscape-mobile-sec d-none">
        <script data-pagespeed-no-defer>//<![CDATA[
        (function () {
            for (
                var g =
                        "function" == typeof Object.defineProperties
                            ? Object.defineProperty
                            : function (b, c, a) {
                                  if (a.get || a.set) throw new TypeError("ES3 does not support getters and setters.");
                                  b != Array.prototype && b != Object.prototype && (b[c] = a.value);
                              },
                    h = "undefined" != typeof window && window === this ? this : "undefined" != typeof global && null != global ? global : this,
                    k = ["String", "prototype", "repeat"],
                    l = 0;
                l < k.length - 1;
                l++
            ) {
                var m = k[l];
                m in h || (h[m] = {});
                h = h[m];
            }
            var n = k[k.length - 1],
                p = h[n],
                q = p
                    ? p
                    : function (b) {
                          var c;
                          if (null == this) throw new TypeError("The 'this' value for String.prototype.repeat must not be null or undefined");
                          c = this + "";
                          if (0 > b || 1342177279 < b) throw new RangeError("Invalid count value");
                          b |= 0;
                          for (var a = ""; b; ) if ((b & 1 && (a += c), (b >>>= 1))) c += c;
                          return a;
                      };
            q != p && null != q && g(h, n, { configurable: !0, writable: !0, value: q });
            var t = this;
            function u(b, c) {
                var a = b.split("."),
                    d = t;
                a[0] in d || !d.execScript || d.execScript("var " + a[0]);
                for (var e; a.length && (e = a.shift()); ) a.length || void 0 === c ? (d[e] ? (d = d[e]) : (d = d[e] = {})) : (d[e] = c);
            }
            function v(b) {
                var c = b.length;
                if (0 < c) {
                    for (var a = Array(c), d = 0; d < c; d++) a[d] = b[d];
                    return a;
                }
                return [];
            }
            function w(b) {
                var c = window;
                if (c.addEventListener) c.addEventListener("load", b, !1);
                else if (c.attachEvent) c.attachEvent("onload", b);
                else {
                    var a = c.onload;
                    c.onload = function () {
                        b.call(this);
                        a && a.call(this);
                    };
                }
            }
            var x;
            function y(b, c, a, d, e) {
                this.h = b;
                this.j = c;
                this.l = a;
                this.f = e;
                this.g = { height: window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight, width: window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth };
                this.i = d;
                this.b = {};
                this.a = [];
                this.c = {};
            }
            function z(b, c) {
                var a,
                    d,
                    e = c.getAttribute("data-pagespeed-url-hash");
                if ((a = e && !(e in b.c)))
                    if (0 >= c.offsetWidth && 0 >= c.offsetHeight) a = !1;
                    else {
                        d = c.getBoundingClientRect();
                        var f = document.body;
                        a = d.top + ("pageYOffset" in window ? window.pageYOffset : (document.documentElement || f.parentNode || f).scrollTop);
                        d = d.left + ("pageXOffset" in window ? window.pageXOffset : (document.documentElement || f.parentNode || f).scrollLeft);
                        f = a.toString() + "," + d;
                        b.b.hasOwnProperty(f) ? (a = !1) : ((b.b[f] = !0), (a = a <= b.g.height && d <= b.g.width));
                    }
                a && (b.a.push(e), (b.c[e] = !0));
            }
            y.prototype.checkImageForCriticality = function (b) {
                b.getBoundingClientRect && z(this, b);
            };
            u("pagespeed.CriticalImages.checkImageForCriticality", function (b) {
                x.checkImageForCriticality(b);
            });
            u("pagespeed.CriticalImages.checkCriticalImages", function () {
                A(x);
            });
            function A(b) {
                b.b = {};
                for (var c = ["IMG", "INPUT"], a = [], d = 0; d < c.length; ++d) a = a.concat(v(document.getElementsByTagName(c[d])));
                if (a.length && a[0].getBoundingClientRect) {
                    for (d = 0; (c = a[d]); ++d) z(b, c);
                    a = "oh=" + b.l;
                    b.f && (a += "&n=" + b.f);
                    if ((c = !!b.a.length))
                        for (a += "&ci=" + encodeURIComponent(b.a[0]), d = 1; d < b.a.length; ++d) {
                            var e = "," + encodeURIComponent(b.a[d]);
                            131072 >= a.length + e.length && (a += e);
                        }
                    b.i && ((e = "&rd=" + encodeURIComponent(JSON.stringify(B()))), 131072 >= a.length + e.length && (a += e), (c = !0));
                    C = a;
                    if (c) {
                        d = b.h;
                        b = b.j;
                        var f;
                        if (window.XMLHttpRequest) f = new XMLHttpRequest();
                        else if (window.ActiveXObject)
                            try {
                                f = new ActiveXObject("Msxml2.XMLHTTP");
                            } catch (r) {
                                try {
                                    f = new ActiveXObject("Microsoft.XMLHTTP");
                                } catch (D) {}
                            }
                        f && (f.open("POST", d + (-1 == d.indexOf("?") ? "?" : "&") + "url=" + encodeURIComponent(b)), f.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"), f.send(a));
                    }
                }
            }
            function B() {
                var b = {},
                    c;
                c = document.getElementsByTagName("IMG");
                if (!c.length) return {};
                var a = c[0];
                if (!("naturalWidth" in a && "naturalHeight" in a)) return {};
                for (var d = 0; (a = c[d]); ++d) {
                    var e = a.getAttribute("data-pagespeed-url-hash");
                    e &&
                        ((!(e in b) && 0 < a.width && 0 < a.height && 0 < a.naturalWidth && 0 < a.naturalHeight) || (e in b && a.width >= b[e].o && a.height >= b[e].m)) &&
                        (b[e] = { rw: a.width, rh: a.height, ow: a.naturalWidth, oh: a.naturalHeight });
                }
                return b;
            }
            var C = "";
            u("pagespeed.CriticalImages.getBeaconData", function () {
                return C;
            });
            u("pagespeed.CriticalImages.Run", function (b, c, a, d, e, f) {
                var r = new y(b, c, a, e, f);
                x = r;
                d &&
                    w(function () {
                        window.setTimeout(function () {
                            A(r);
                        }, 0);
                    });
            });
        })();
        pagespeed.CriticalImages.Run("/mod_pagespeed_beacon", "http://localhost:2020/annualsurvey/getSurveyDtls", "nXzXivl0t7", true, false, "QmMTKvq4hDM");
        
//]]></script>
        <p>Please Turn your device to portrait mode.</p>
      </div>
         <header class="menu-main">
            <div class="row align-center">
               <div class="col-8">
                  <div class="logo">
                     <a href="login.php">
                       <img src="assets/images/mahindra-rise-logo.png" data-pagespeed-url-hash="451838461" onload="pagespeed.CriticalImages.checkImageForCriticality(this);">
                   </a>
                     <span class="logo-border">Group HR Annual Survey</span>
                  </div>
               </div>
               <div class="col-4">
                  <ul>
                     <li class="profile-border">
                        <img src="assets/images/profile.png" data-pagespeed-url-hash="3390529042" onload="pagespeed.CriticalImages.checkImageForCriticality(this);">
                        <span>
                           <svg id="baseline-keyboard_arrow_down-24px" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                              <path id="Path_1411" data-name="Path 1411" d="M7.41,8.59,12,13.17l4.59-4.58L18,10l-6,6L6,10Z" fill="#fff"/>
                              <path id="Path_1412" data-name="Path 1412" d="M0,0H24V24H0Z" fill="none"/>
                           </svg>
                        </span>
                        <div class="accountbox">
                           <ul class="inner-ul">
                              <li class="signout"><a href="login.php">Signout</a></li>
                           </ul>
                        </div>
                     </li>
                  </ul>
               </div>
            </div>
         </header>
<section class="survey survey-main-page">
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
</section>
<div class="main-wrap position-relative survey-main-page">
   <div class="container-fluid padd-zero">
      <div class="scroll_horizontal">
          <ul class="survey_heading_div survey-top-heading">
                           <h4 class="cout_span d-block d-lg-none">
                           <span>0</span>/<i>9</i>
                           </h4>
                             <%int tabid=1; %>
                           <c:forEach items="${areas}" var="role" varStatus="status">
                           	 <%tabid++;%>
                           <li class="" id="li_<%=tabid%>" data-goto="content_<%=tabid%>" data-index="<%=tabid%>">
                            
                              <i class="d-none"></i>
                              <div class="text_div">
                                 <!-- <h4>MeCentral Support</h4> -->
                                 <h4>${role.areaDesc}</h4>
                              </div>
                              <div class="progress">
  								<div class="progress-bar" role="progressbar" style="width: 0%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
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
                           <%int radioCnt =0; int x=1;  int contid=1; %> 
 							<input type="hidden" id="surveyId" name="surveyId" value="${surveyId}">			
							 <input type="hidden" id="Empid" name="Empid" value="${empId}">
							 <input type="hidden" id="pkEmp" name="pkEmp" value="${pkEmp}">				 
						     <input type="hidden" id="year" name="year">				 
						     <input type="hidden" id="operation" name="operation" value="N">				 
						     <input type="hidden" id="responsemap" name="responsemap" value="">				 
							 <c:set var="iter" value="0" />
							 
							 <%-- <c:out value="${pkEmp}">Niki7777777777777777</c:out> --%>
							 
							 
 							<c:forEach items="${areas}" var="role" varStatus="status">
 							 <%radioCnt++;%>
 							  <%contid++;%>
                               <div class="inner_content_div" id="content_<%=contid%>" data-li="li_<%=contid%>">
                                 <ul class="question_ul_inner">
                                 	<%int a = 0;String b;int c = 65;int d=0; %>
                                  
                                     <c:forEach items="${areaquestion}" var="role1" varStatus="status1">
                                    	
                                       <c:if test="${role1.questionArea.areaDesc==role.areaDesc}" > 
					           			<%++a;%>
					           			
					           			
						          		 <% if (a % 2 == 0) {
												  b="gray";
												 } else { 
												  b="white";
												 }  
						          			 pageContext.setAttribute("b", b);
				 						%>
                                     <li class=" main-que last_click">
                                       <%--  <c:forEach items="${areaquestion}" var="role1" varStatus="status1"> --%>
                                       
                                       <!-- <p>Manager level scorecard will enable action planning.</p> -->
                                       <h4>${role.areaDesc}</h4>
                                       <p><span><%=a %>. &nbsp;</span>${role1.questionDesc}</p>
                                    	
                                    	
                                    	<c:if test="${role1.questType.pkQuestType=='1'}">
                                        <div class="switch-field">
    									<input type="hidden" name="tblresponsedtls[${status1.index}].questionType" value="Rate">
                                         
                                          <input type="radio" class="radio-<%=radioCnt%>" id="Resp${role1.questionId}rad1" name="tblresponsedtls[${status1.index}].answerNo" value="1">
                                          <label for="Resp${role1.questionId}rad1" class="border-right-0"  >Strongly Disagree</label>
                                        
                                         <%++radioCnt;%>
                                          <input type="radio" class="radio-<%=radioCnt%>" id="Resp${role1.questionId}rad2" name="tblresponsedtls[${status1.index}].answerNo" value="2">
                                          <label for="Resp${role1.questionId}rad2" class="border-right-0">Disagree</label>                        
                                          
                                          <%++radioCnt;%>
                                          <input type="radio" class="radio-<%=radioCnt%>" id="Resp${role1.questionId}rad3" name="tblresponsedtls[${status1.index}].answerNo" value="3">
                                          <label for="Resp${role1.questionId}rad3" class="border-right-0">Neutral</label>
                                          <%++radioCnt;%>
                                          <input type="radio" class="radio-<%=radioCnt%>" id="Resp${role1.questionId}rad4" name="tblresponsedtls[${status1.index}].answerNo" value="4">
                                          <label for="Resp${role1.questionId}rad4" class="border-right-0">agree</label>
                                          <%++radioCnt;%>
                                          <input type="radio" class="radio-<%=radioCnt%>" id="Resp${role1.questionId}rad5"  name="tblresponsedtls[${status1.index}].answerNo" value="5">
                                          <label for="Resp${role1.questionId}rad5" class="border-right-0">Strongly Agree</label>
                                          <%++radioCnt;%>
                                           <input type="radio" class="radio-<%=radioCnt%>" id="Resp${role1.questionId}rad0" name="tblresponsedtls[${status1.index}].answerNo" value="0">
                                          <label for="Resp${role1.questionId}rad0">N/A</label>
                                          
                                            <input type="radio"  name="tblresponsedtls[${status1.index}].answerNo" value="-1" checked="checked" class="defaultRadio">
                                       </div>  
                                        </c:if>
                                        
                                         <c:if test="${role1.questType.pkQuestType=='2'}">          
									      <div class="question-type-2 add-comment mt-5">
									      <input type="hidden" name="tblresponsedtls[${status1.index}].questionType" value="OpenEnded">
                                          
                                          <%-- <textarea class="form-control must radio-custom1" id="Resp${role1.questionId}text" 
                                          name="tblresponsedtls[${status1.index}].answerText" maxlength="1000"></textarea>
                                           --%>
                                          
                                          <textarea class="form-control must radio-custom1" id="Resp${role1.questionId}text" 
                                          name="tblresponsedtls[${status1.index}].answerText" maxlength="1000"
                                          placeholder="This a very nice initiative taken by HR department">
                                          </textarea>
                                          
                                          <a class="skip " style="display: none;">skip</a>
                                          <button type="button" class=" ok-btn" style="display: inline-block;">Ok</button>
                                         </div>
        								</c:if>  
                                    
                                     	 <c:if test="${role1.questType.pkQuestType=='3'}">          
	            						 <input type="hidden" name="tblresponsedtls[${status1.index}].questionType" value="Rate">
	            						 <ul class="switch-field question-type-3">
                                          <li>
                                             <c:if test="${iter eq role1.questType.pkQuestType}">
                                             <p class="sub-ques">A. Rise Stories</p>
                                             </c:if>
                                             <input type="radio" id="Resp${role1.questionId}rad1" name="tblresponsedtls[${status1.index}].answerNo" value="1">
                                             <!-- <input type="radio" id="rise-1" name="rise-1" > -->
                                             <label for="Resp${role1.questionId}rad1">1</label>
                                             
                                              <input type="radio" id="Resp${role1.questionId}rad2" name="tblresponsedtls[${status1.index}].answerNo" value="2">
                                             <label for="Resp${role1.questionId}rad2">2</label>
                                             
                                             <input type="radio" id="Resp${role1.questionId}rad3" name="tblresponsedtls[${status1.index}].answerNo" value="3">
                                             <label for="Resp${role1.questionId}rad3">3</label>
                                             
                                             <input type="radio" id="Resp${role1.questionId}rad4" name="tblresponsedtls[${status1.index}].answerNo" value="4">
                                             <label for="Resp${role1.questionId}rad4">4</label>
                                             
                                             <input type="radio" id="Resp${role1.questionId}rad5"  name="tblresponsedtls[${status1.index}].answerNo" value="5">
                                             <label for="Resp${role1.questionId}rad5">5</label>
                                             
                                             <input type="radio" id="Resp${role1.questionId}rad0" name="tblresponsedtls[${status1.index}].answerNo" value="0">
                                             <label for="Resp${role1.questionId}rad0">NA</label>
                                             
                                             <div class="col-sm-2 col-xs-2 text-center radio-custom">
								             <input type="radio"  name="tblresponsedtls[${status1.index}].answerNo" value="-1"  checked="checked" class="defaultRadio">
								             </div>
                                          </li>
	            						</ul>
             							 </c:if>  
             							 
    
            							<c:if test="${role1.questType.pkQuestType=='4'}">
                  						<input type="hidden" name="tblresponsedtls[${status1.index}].questionType" value="Subquestion">
                  						  <c:forEach items="${role1.mtsubquestion}" var="role2" varStatus="status2">
                  						  
                  						   <input type="hidden" name="tblresponsedtls[${status1.index}].mtrespsub[${status2.index}].subquestionId" value="${role2.subQuestionId}">         
								           <input type="hidden" name="tblresponsedtls[${status1.index}].mtrespsub[${status2.index}].questionBankId" value="${role1.questionId}">
                  						  
                  						  <div class="col-sm-6 ">
									       <p class="subq"><%=(char)c++ %>. ${role2.subQuestDesc}</p>
									       </div>
									       
                  						  <div class="switch-field">
                                          
                                          <input type="radio" id="Resp${role2.subQuestionId}subquest1" name="tblresponsedtls[${status1.index}].mtrespsub[${status2.index}].answerNo" value="1">
                                          <label for="Resp${role2.subQuestionId}subquest1" class="border-right-0"  >Very Low</label>
                                         <%++radioCnt;%>
                                         
                                          <input type="radio" id="Resp${role2.subQuestionId}subquest2" name="tblresponsedtls[${status1.index}].mtrespsub[${status2.index}].answerNo" value="2">
                                          <label for="Resp${role2.subQuestionId}subquest2" class="border-right-0">Low</label>                        
                                          <%++radioCnt;%>
                                         
                                          <input type="radio" id="Resp${role2.subQuestionId}subquest3" name="tblresponsedtls[${status1.index}].mtrespsub[${status2.index}].answerNo" value="3">
                                          <label for="Resp${role2.subQuestionId}subquest3" class="border-right-0">Neutral</label>
                                          <%++radioCnt;%>
                                         
                                          <input type="radio" id="Resp${role2.subQuestionId}subquest4" name="tblresponsedtls[${status1.index}].mtrespsub[${status2.index}].answerNo" value="4">
                                          <label for="Resp${role2.subQuestionId}subquest4" class="border-right-0">High</label>
                                          <%++radioCnt;%>
                                         
                                          <input type="radio" id="Resp${role2.subQuestionId}subquest5"  name="tblresponsedtls[${status1.index}].mtrespsub[${status2.index}].answerNo" value="5">
                                          <label for="Resp${role2.subQuestionId}subquest5" class="border-right-0">Very High</label>
                                          <%++radioCnt;%>
                                         
                                          <input type="radio" id="Resp${role2.subQuestionId}subquest0" name="tblresponsedtls[${status1.index}].mtrespsub[${status2.index}].answerNo" value="0">
                                          <label for="Resp${role2.subQuestionId}subquest0">N/A</label>
                                       </div>
                                       
                                       <div class="col-sm-2 col-xs-2 text-center radio-custom" style="display:none">
						               <input type="radio" name="tblresponsedtls[${status1.index}].mtrespsub[${status2.index}].answerNo" value="-1" checked="checked"  class="defaultRadio">
						               </div>
						               
                                       </c:forEach>            
               						  </c:if>  
										
										 <c:if test="${role1.questType.pkQuestType=='5'}"> 
										
										<div class="switch-field yes-no">
										 <input type="hidden" name="tblresponsedtls[${status1.index}].questionType" value="Rate">
                                          
                                          <input type="radio" id="Resp${role1.questionId}rad1" name="tblresponsedtls[${status1.index}].answerNo" value="1">
                                          <label for="radio-61" class="border-right-0">Yes </label>
                                          
                                          <input type="radio" id="Resp${role1.questionId}rad2" name="tblresponsedtls[${status1.index}].answerNo" value="2">
                                          <label for="radio-62" class="">No</label>                        
                                        
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
			
         								 </c:if>
         								 
							              <c:if test="${role1.questType.pkQuestType=='6'}">          
							        <div class="switch-field">
							        <input type="hidden" name="tblresponsedtls[${status1.index}].questionType" value="Rate">
							              
							              <input type="radio" id="Resp${role1.questionId}rad1" name="tblresponsedtls[${status1.index}].answerNo" value="1">
                                          <label for="Resp${role1.questionId}rad1" class="border-right-0"  >Never</label>
                                         <%++radioCnt;%>
                                         
                                         <input type="radio" id="Resp${role1.questionId}rad2" name="tblresponsedtls[${status1.index}].answerNo" value="2">
                                          <label for="Resp${role1.questionId}rad2" class="border-right-0">Once in 2 months</label>                        
                                          <%++radioCnt;%>
                                         
                                         <input type="radio" id="Resp${role1.questionId}rad3" name="tblresponsedtls[${status1.index}].answerNo" value="3">
                                          <label for="Resp${role1.questionId}rad3" class="border-right-0">Once in a month</label>
                                          <%++radioCnt;%>
                                         
                                         <input type="radio" id="Resp${role1.questionId}rad4" name="tblresponsedtls[${status1.index}].answerNo" value="4">
                                          <label for="Resp${role1.questionId}rad4" class="border-right-0">Once a week</label>
                                          <%++radioCnt;%>
                                         
                                         <input type="radio" id="Resp${role1.questionId}rad5" name="tblresponsedtls[${status1.index}].answerNo" value="5">
                                          <label for="Resp${role1.questionId}rad5" class="border-right-0">Once a day</label>
                                          <%++radioCnt;%>
                                         
                                         <input type="radio" id="Resp${role1.questionId}rad0" name="tblresponsedtls[${status1.index}].answerNo" value="0">
                                          <label for="Resp${role1.questionId}rad6">N/A</label>
                                          
                                             <div class="col-sm-2 col-xs-2 text-center radio-custom">
								              <input type="radio"  name="tblresponsedtls[${status1.index}].answerNo" value="-1" checked="checked" class="defaultRadio">
								            </div>
                                          
                                       </div>  
							        </c:if>  
                                    </li>
                                    </c:if>
                                    </c:forEach>
                                 </ul>
                                 <script>
                                 
                                 </script>
                              </div>
                              <div class="question-type-2 add-comment">
                                          <input type="checkbox" id="Comments${role.areaCode}" class="comment addcmntchk" >
                                          <label for="Comments${role.areaCode}">ADD Comment</label>
                                          <textarea id="dvComments${role.areaCode}text" placeholder="Please enter your comments">
                                          </textarea>
                                          <a class="skip ">skip</a>

                                          <button type="button" class=" ok-btn" >Ok</button>
                              </div>
                              
               <%--                <div class="form-group addCommentDiv">  
	<div class="col-sm-12 bttompadng">
	<div class="col-sm-6">
   <label class="col-sm-3 nopad chkpssprt" for="chkPassport">
      
        Add Comments
    </label>
    <input type="checkbox" id="Comments${role.areaCode}" class="comment addcmntchk" style="    position: relative;	    top: 6px;" />
    </div>
    <div class="col-sm-5 comment2 txtdiv" id="dvComments${role.areaCode}" name="" style="display: none; padding-right: 0;">
       <textarea type="text" class="form-control Addcomments" id="dvComments${role.areaCode}text" maxlength="1000">
       </textarea>
    </div>
 </div>
</div> --%>
                              
                              </c:forEach>
                           </div> 
                        </div>
                     </div>
                  </div>
               </div>
               <div class="btn-groups survey-btns">
               <button type="button" class="survey-btn save-survey" id="saveUser" onclick="saveUserResponse()" > Save survey </button>
                  <button type="button" class="survey-btn submit-btn" id="submitUser" onclick="submitUserResponse()" > Submit </button>
                  <!-- <a href="javascript:void(0);" class="survey-btn save-survey">Save survey</a>
                  <a href="thank-you.php" class="survey-btn submit-btn" style="display: none;">Submit</a> -->
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
                  <img src="assets/images/document.svg" data-pagespeed-url-hash="3595073531" onload="pagespeed.CriticalImages.checkImageForCriticality(this);">
               </div>
               <div class="man-img">
                  <h4 class="cout_span"><span>0</span>/<i>19</i></h4>
                  <img src="assets/images/survey-man-img.svg" data-pagespeed-url-hash="4034878773" onload="pagespeed.CriticalImages.checkImageForCriticality(this);">
               </div>
            </div>
         </div>
         <div class="col-md-1">
         </div>
      </div>
   </div>
</div>
 <%@ include file = "footer.jsp" %>