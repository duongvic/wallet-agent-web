<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title>Trợ giúp - <spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp"/>
<!-- /menu bar -->

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active">Trợ giúp</li>
    </ol>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-xl-3 col-md-4">
        <!-- Panel -->
        <div class="panel">
          <div class="panel-body">
            <div class="list-group faq-list" role="tablist">
              <a class="list-group-item list-group-item-action active" data-toggle="tab" href="#category-1" aria-controls="category-1" role="tab">General</a>
              <a class="list-group-item" data-toggle="tab" href="#category-2" aria-controls="category-2" role="tab">Payment</a>
              <a class="list-group-item" data-toggle="tab" href="#category-3" aria-controls="category-3" role="tab">Offers</a>
              <a class="list-group-item" data-toggle="tab" href="#category-4" aria-controls="category-4" role="tab">Security and Privacy</a>
            </div>
          </div>
        </div>
        <!-- End Panel -->
      </div>
      <div class="col-xl-9 col-md-8">
        <!-- Panel -->
        <div class="panel">
          <div class="panel-body">
            <div class="tab-content">
              <!-- Categroy 1 -->
              <div class=" tab-pane animation-fade active" id="category-1" role="tabpanel">
                <div class="panel-group panel-group-simple panel-group-continuous" id="accordion2" aria-multiselectable="true" role="tablist">
                  <!-- Question 1 -->
                  <div class="panel">
                    <div class="panel-heading" id="question-1" role="tab">
                      <a class="panel-title" aria-controls="answer-1" aria-expanded="true" data-toggle="collapse" href="#answer-1" data-parent="#accordion2">
                      What is Lorem ipsum?
                    </a>
                    </div>
                    <div class="panel-collapse collapse show" id="answer-1" aria-labelledby="question-1" role="tabpanel">
                      <div class="panel-body"> Lorem ipsum Voluptate ad et culpa ad eu ex nulla voluptate nostrud consectetur nisi nisi incididunt voluptate enim labore occaecat qui laboris id sunt
                        aute mollit reprehenderit aute non dolor labore tempor Duis ex deserunt proident elit enim culpa labore fugiat labore velit ut ea aute Ut laboris culpa consectetur ex tempor
                        cillum consectetur enim do aute ut commodo mollit aute esse enim fugiat quis Excepteur enim in qui nostrud et ad nulla tempor cupidatat fugiat proident ut proident elit
                        cupidatat laborum in fugiat amet elit mollit sit commodo reprehenderit enim minim sit Duis laboris dolor velit sed dolore consequat ea magna ut laborum incididunt nostrud do
                        non nisi minim anim dolor anim ex adipisicing ut ex Ut cupidatat consectetur ut magna est minim proident occaecat aliquip consectetur adipisicing dolor ea nulla dolore commodo
                        reprehenderit velit nulla eu dolor proident aliqua elit est aliqua labore eiusmod.
                      </div>
                    </div>
                  </div>
                  <!-- End Question 1 -->
                  <!-- Question 2 -->
                  <div class="panel">
                    <div class="panel-heading" id="question-2" role="tab">
                      <a class="panel-title" aria-controls="answer-2" aria-expanded="false" data-toggle="collapse" href="#answer-2" data-parent="#accordion2">
                      How much does it cost to use Lorem ipsum?
                    </a>
                    </div>
                    <div class="panel-collapse collapse" id="answer-2" aria-labelledby="question-2" role="tabpanel">
                      <div class="panel-body"> Laborum commodo cupidatat adipisicing aliqua qui in dolore occaecat labore nisi occaecat enim dolor sit exercitation sit Duis minim tempor est aliquip
                        sit nostrud ea ea sit nostrud dolore cillum exercitation officia sunt pariatur consequat velit id nulla id Duis minim sunt sint culpa amet veniam commodo nisi reprehenderit
                        tempor irure sunt enim in eu reprehenderit anim nulla tempor pariatur nisi fugiat consectetur sint deserunt elit voluptate in in labore eiusmod nostrud eiusmod est ullamco sit
                        qui ut ut velit in veniam in Ut nulla pariatur reprehenderit deserunt laboris sed cupidatat est dolore adipisicing Duis in ut dolore dolor sunt aute ut Excepteur sint
                        consectetur.
                      </div>
                    </div>
                  </div>
                  <!-- End Question 2 -->
                  <!-- Question 3 -->
                  <div class="panel">
                    <div class="panel-heading" id="question-3" role="tab">
                      <a class="panel-title" aria-controls="answer-3" aria-expanded="false" data-toggle="collapse" href="#answer-3" data-parent="#accordion2">
                      What is the lorem ipsum pariatur?
                    </a></div>
                    <div class="panel-collapse collapse" id="answer-3" aria-labelledby="question-3" role="tabpanel">
                      <div class="panel-body"> Lorem ipsum Esse pariatur sit reprehenderit non culpa sint ullamco qui in aliquip enim exercitation laborum ut eu voluptate exercitation Duis dolore amet
                        pariatur id occaecat incididunt deserunt nulla esse proident.
                      </div>
                    </div>
                  </div>
                  <!-- End Question 3 -->
                  <!-- Question 4 -->
                  <div class="panel">
                    <div class="panel-heading" id="question-4" role="tab">
                      <a class="panel-title" aria-controls="answer-4" aria-expanded="false" data-toggle="collapse" href="#answer-4" data-parent="#accordion2">
                      Lorem ipsum commodo adipisicing sunt ad aliqua velit nulla?
                    </a>
                    </div>
                    <div class="panel-collapse collapse" id="answer-4" aria-labelledby="question-4" role="tabpanel">
                      <div class="panel-body"> Lorem ipsum Do cupidatat in culpa magna voluptate ullamco et anim nulla cupidatat dolor culpa consequat quis dolor eu aliqua Ut eiusmod ullamco sint
                        reprehenderit commodo veniam ea irure sit reprehenderit sunt sed sed proident ea incididunt esse cillum cupidatat officia Duis.
                      </div>
                    </div>
                  </div>
                  <!-- End Question 4 -->
                </div>
              </div>
              <!-- End Categroy 1 -->
              <!-- Categroy 2 -->
              <div class="tab-pane animation-fade" id="category-2" role="tabpanel">
                <div class="panel-group panel-group-simple panel-group-continuous" id="accordion" aria-multiselectable="true" role="tablist">
                  <!-- Question 5 -->
                  <div class="panel">
                    <div class="panel-heading" id="question-5" role="tab">
                      <a class="panel-title" aria-controls="answer-5" aria-expanded="true" data-toggle="collapse" href="#answer-5"
                                                                             data-parent="#accordion">
                      Can I exercitation occaecat voluptate duis ut dolore?
                    </a></div>
                    <div class="panel-collapse collapse show" id="answer-5" aria-labelledby="question-5" role="tabpanel">
                      <div class="panel-body"> Consecutus sicine hominum, artem conclusionemque patientia, omni fuerit intemperantiam efficeretur phaedrum minime obiecta constituam, motus mandamus
                        perfunctio contra, imagines exquisitis reiciat.
                      </div>
                    </div>
                  </div>
                  <!-- End Question 5 -->
                  <!-- Question 6 -->
                  <div class="panel">
                    <div class="panel-heading" id="question-6" role="tab">
                      <a class="panel-title" aria-controls="answer-6" aria-expanded="false" data-toggle="collapse" href="#answer-6"
                                                                             data-parent="#accordion">
                      How do lorem ipsum quis magna magna?
                    </a></div>
                    <div class="panel-collapse collapse" id="answer-6" aria-labelledby="question-6" role="tabpanel">
                      <div class="panel-body"> Pluribus horum laetamur forensibus investigandi gravissimis quaeque possumus copulationesque. Sapientium interdictum, secutus servata expressas reici.
                        Dixerit dixerit tantis conscientiam arbitramur reprehensa traditur propositum locos remotis, metus continent maledici attico sermo iucundius gerendarum. Bonarum incommoda
                        afferrent iisque graeca perdiscere munere recordatione nos sapiens, perspexit, oderit nominata.
                      </div>
                    </div>
                  </div>
                  <!-- End Question 6 -->
                  <!-- Question 7 -->
                  <div class="panel">
                    <div class="panel-heading" id="question-7" role="tab">
                      <a class="panel-title" aria-controls="answer-7" aria-expanded="false" data-toggle="collapse" href="#answer-7"
                                                                             data-parent="#accordion">
                      How do I set up lorem ipsum?
                    </a></div>
                    <div class="panel-collapse collapse" id="answer-7" aria-labelledby="question-7" role="tabpanel">
                      <div class="panel-body"> Acute ennius fuisset facile plena quem possim tribus antipatrum moveat, praestabiliorem geometria inportuno privamur, remissius habemus desperantes,
                        nullas quas terrore modum uberius homine adiuvet, dissentio iudicabit ac, nullus molita foedus num. Circumcisaque nulla aut etenim doceat pecunias afferrent infinitis,
                        propterea repellat epularum inveneris dissentiet meminerunt malivoli gloriosis, faciunt petat conscientia, nesciunt logikh fortunae, primos expedire aliquo putet aptissimum
                        officiis naturalem.
                      </div>
                    </div>
                  </div>
                  <!-- End Question 7 -->
                </div>
              </div>
              <!-- End Categroy 2 -->
              <!-- Categroy 3 -->
              <div class="tab-pane animation-fade" id="category-3" role="tabpanel">
                <div class="panel-group panel-group-simple panel-group-continuous" id="accordion1" aria-multiselectable="true" role="tablist">
                  <!-- Question 8 -->
                  <div class="panel">
                    <div class="panel-heading" id="question-8" role="tab">
                      <a class="panel-title" aria-controls="answer-8" aria-expanded="true" data-toggle="collapse" href="#answer-8"
                                                                             data-parent="#accordion1">
                      How can I lorem ipsum elit commodo?
                    </a></div>
                    <div class="panel-collapse collapse show" id="answer-8" aria-labelledby="question-8" role="tabpanel">
                      <div class="panel-body"> Lorem ipsum Est dolore eiusmod sunt fugiat sunt minim qui ullamco incididunt mollit.</div>
                    </div>
                  </div>
                  <!-- End Question 8 -->
                  <!-- Question 9 -->
                  <div class="panel">
                    <div class="panel-heading" id="question-9" role="tab">
                      <a class="panel-title" aria-controls="answer-9" aria-expanded="false" data-toggle="collapse" href="#answer-9"
                                                                             data-parent="#accordion1">
                      Where can I lorem ipsum efficere sed ullamco?
                    </a></div>
                    <div class="panel-collapse collapse" id="answer-9" aria-labelledby="question-9" role="tabpanel">
                      <div class="panel-body"> Solitudo per mox coerceri efficere vetuit exhorrescere, silano sitisque efficere iniurias adhaesiones agam dolores ponit multa. Exiguam ii genus
                        libidinosarum scribere, hinc ipsius severitatem falsis desistemus praeteritas cogitemus delectu terentii, pedalis angusta scripserit sit iucundum mirari ipsi parta. Maluisti
                        emolumento solido gravissimis amicitia ornatum eademque scribi terentianus tollit, tranquillitatem intellegentium persequeris ordiamur declinationem optio detrimenti inclusae,
                        augendas semper.
                      </div>
                    </div>
                  </div>
                  <!-- End Question 9 -->
                  <!-- Question 10 -->
                  <div class="panel">
                    <div class="panel-heading" id="question-10" role="tab">
                      <a class="panel-title" aria-controls="answer-10" aria-expanded="false" data-toggle="collapse" href="#answer-10"
                                                                              data-parent="#accordion1">
                      Do I have to torquate artem irure et quis dolor?
                    </a></div>
                    <div class="panel-collapse collapse" id="answer-10" aria-labelledby="question-10" role="tabpanel">
                      <div class="panel-body"> Exeamus, frustra, imperitorum torquate artem efficeret talem vivendi, primum libidinem virtus apte incurrunt, appetendum utramque, oderis. Homines
                        traditur loquerer fugiat sequitur gravitate nisi recordatione efflorescere. Primisque hostis prorsus praeda, solitudo labefactetur miserum chrysippi afficit deterret ipsam.
                      </div>
                    </div>
                  </div>
                  <!-- End Question 10 -->
                </div>
              </div>
              <!-- End Categroy 3 -->
              <!-- Categroy 4 -->
              <div class="tab-pane animation-fade" id="category-4" role="tabpanel">
                <div class="panel-group panel-group-simple panel-group-continuous" id="accordion3" aria-multiselectable="true" role="tablist">
                  <!-- Question 11 -->
                  <div class="panel">
                    <div class="panel-heading" id="question-11" role="tab">
                      <a class="panel-title" aria-controls="answer-11" aria-expanded="true" data-toggle="collapse" href="#answer-11"
                                                                              data-parent="#accordion3">
                      What if caritatem interesse nulla ut non esse laborum?
                    </a></div>
                    <div class="panel-collapse collapse show" id="answer-11" aria-labelledby="question-11" role="tabpanel">
                      <div class="panel-body"> Malint docui o animos opus confidam isti animadvertat sententiae, imagines faciendumve varias dolemus detrimenti caritatem, interesse primos perfecto.
                      </div>
                    </div>
                  </div>
                  <!-- End Question 11 -->
                  <!-- Question 12 -->
                  <div class="panel">
                    <div class="panel-heading" id="question-12" role="tab">
                      <a class="panel-title" aria-controls="answer-12" aria-expanded="false" data-toggle="collapse" href="#answer-12"
                                                                              data-parent="#accordion3">
                      Do my tempor fugiat dolore culpa commodo ex?
                    </a></div>
                    <div class="panel-collapse collapse" id="answer-12" aria-labelledby="question-12" role="tabpanel">
                      <div class="panel-body"> Lorem ipsum Aliquip aute ut tempor consectetur laboris ea aliqua Duis Excepteur laboris consequat enim dolore dolore ut officia esse sunt ut voluptate
                        cupidatat.
                      </div>
                    </div>
                  </div>
                  <!-- End Question 12 -->
                  <!-- Question 13 -->
                  <div class="panel">
                    <div class="panel-heading" id="question-13" role="tab">
                      <a class="panel-title" aria-controls="answer-13" aria-expanded="false" data-toggle="collapse" href="#answer-13"
                                                                              data-parent="#accordion3">
                      How do I metuamus ipsum nostrud commodo aliquip?
                    </a></div>
                    <div class="panel-collapse collapse" id="answer-13" aria-labelledby="question-13" role="tabpanel">
                      <div class="panel-body"> Illo scribi depulsa atomus puto omnium legat, plus pater. Siculis voluptate repellendus debilitatem moderatio angatur careret, integre quale sollicitant,
                        potest fallare attingere theophrasti gratiam quasi, vivendum solet metuamus declinare, elegans detractis. Loquuntur coerceri solitudo optabilem transferre malum. Desiderat.
                        Didicisset. Timiditatem, aliena, tranquilli dicebas sicine. Conquirendae refugiendi desideret disserunt probaretur, brevis iure eidola intemperantiam dixisset quiete quaerenda
                        ferae patria, dicemus declinabunt congressus, auctori referuntur, aliquod consumere consumeret quicquam mors, longinquitate tenebimus alienae sensu impeditur, uberius
                        splendore, aegritudo rem cognitionem alienus quamvis.
                      </div>
                    </div>
                  </div>
                  <!-- End Question 13 -->
                </div>
              </div>
              <!-- End Categroy 4 -->
            </div>
          </div>
        </div>
        <!-- End Panel -->
      </div>
    </div>
  </div>
</div>
<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
</body>

</html>