package syndexmx.github.com.tgsiren.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static syndexmx.github.com.tgsiren.utils.HtmlBlockExtractor.extractAllClassedBlocks;
import static syndexmx.github.com.tgsiren.utils.HtmlBlockExtractor.extractAllTaggedBlocks;

public class HtmlBlockExtractorTest {

    String tag = "div";


    @Test
    void extractAllTaggedBlocksPositiveTest() {
        List<String> list = extractAllTaggedBlocks(html, tag);
        System.out.println(((Integer)list.size()).toString());
        assertTrue(list.size() > 0);
    }

    String className = "tgme_widget_message_wrap";

    @Test
    void extractAllClassedBlocksPositiveTest() {
        List<String> list = extractAllClassedBlocks(html, tag, className);
        System.out.println(list.toString());
        assertTrue(list.size() > 0);
    }

    String html = """
             <div class="tgme_widget_message_wrap js-widget_message_wrap">
                   <div class="tgme_widget_message text_not_supported_wrap js-widget_message" data-post="mchs_bryansk/4656" data-view="eyJjIjotMTc2OTY3NDQyNywicCI6IjQ2NTZnIiwidCI6MTczMzQyMjQyMiwiaCI6IjgxYjY5NGE0YWE3MGJmNzYwMSJ9">
                    <div class="tgme_widget_message_user">
                     <a href="https://t.me/mchs_bryansk"><i class="tgme_widget_message_user_photo bgcolor3" data-content="М"><img src="https://cdn4.cdn-telegram.org/file/Xhyac5V1Lr_RRFRmipiZTjl_-mnKDvtoUn57mebYz6Ow34cZ67A1K8N02oJTdwZahM3S3__Ily0M31QPfc1lb9M7sQOTSQRclYcZFe9Fs0gF7FR_uSrhef9Ud55b_1nwBg02I-2yMxrjrspXCfUhP12he5ku_CetG0LHcSrii98PaoC6zioIM9dw_6sbwnYXT3a5NOscrgbSYFAF18gytJv8LZzoHbQGTNVj_ZSVxDlX-YhmXy2zOmcLW_Q8O2NzCOllEXNbG-ZkvMABrZIbx-KKaihY2grESasMRtGP4u0mx_VDtjicf-ce2a1Mwxj4f7UBEwNKUVIYSQ4iXnWdWA.jpg" /></i></a>
                    </div>
                    <div class="tgme_widget_message_bubble">
                     <i class="tgme_widget_message_bubble_tail">
                      <svg class="bubble_icon" width="9px" height="20px" viewbox="0 0 9 20">
                       <g fill="none">
                        <path class="background" fill="#ffffff" d="M8,1 L9,1 L9,20 L8,20 L8,18 C7.807,15.161 7.124,12.233 5.950,9.218 C5.046,6.893 3.504,4.733 1.325,2.738 L1.325,2.738 C0.917,2.365 0.89,1.732 1.263,1.325 C1.452,1.118 1.72,1 2,1 L8,1 Z"></path>
                        <path class="border_1x" fill="#d7e3ec" d="M9,1 L2,1 C1.72,1 1.452,1.118 1.263,1.325 C0.89,1.732 0.917,2.365 1.325,2.738 C3.504,4.733 5.046,6.893 5.95,9.218 C7.124,12.233 7.807,15.161 8,18 L8,20 L9,20 L9,1 Z M2,0 L9,0 L9,20 L7,20 L7,20 L7.002,18.068 C6.816,15.333 6.156,12.504 5.018,9.58 C4.172,7.406 2.72,5.371 0.649,3.475 C-0.165,2.729 -0.221,1.464 0.525,0.649 C0.904,0.236 1.439,0 2,0 Z"></path>
                        <path class="border_2x" d="M9,1 L2,1 C1.72,1 1.452,1.118 1.263,1.325 C0.89,1.732 0.917,2.365 1.325,2.738 C3.504,4.733 5.046,6.893 5.95,9.218 C7.124,12.233 7.807,15.161 8,18 L8,20 L9,20 L9,1 Z M2,0.5 L9,0.5 L9,20 L7.5,20 L7.5,20 L7.501,18.034 C7.312,15.247 6.64,12.369 5.484,9.399 C4.609,7.15 3.112,5.052 0.987,3.106 C0.376,2.547 0.334,1.598 0.894,0.987 C1.178,0.677 1.579,0.5 2,0.5 Z"></path>
                        <path class="border_3x" d="M9,1 L2,1 C1.72,1 1.452,1.118 1.263,1.325 C0.89,1.732 0.917,2.365 1.325,2.738 C3.504,4.733 5.046,6.893 5.95,9.218 C7.124,12.233 7.807,15.161 8,18 L8,20 L9,20 L9,1 Z M2,0.667 L9,0.667 L9,20 L7.667,20 L7.667,20 L7.668,18.023 C7.477,15.218 6.802,12.324 5.64,9.338 C4.755,7.064 3.243,4.946 1.1,2.983 C0.557,2.486 0.52,1.643 1.017,1.1 C1.269,0.824 1.626,0.667 2,0.667 Z"></path>
                       </g>
                      </svg> </i>
                     <div class="tgme_widget_message_author accent_color">
                      <a class="tgme_widget_message_owner_name" href="https://t.me/mchs_bryansk"><span dir="auto">МЧС Брянской области</span></a>
                     </div>
                     <div class="media_supported_cont">
                      <div class="tgme_widget_message_one_media js-message_media">
                       <div class="media_supported_cont">
                        <div class="tgme_widget_message_grouped_wrap js-message_grouped_wrap" data-margin-w="2" data-margin-h="2" style="width:453px;">
                         <div class="tgme_widget_message_grouped js-message_grouped" style="padding-top:100%">
                          <div class="tgme_widget_message_grouped_layer js-message_grouped_layer" style="width:453px;height:453px">
                           <a class="tgme_widget_message_photo_wrap grouped_media_wrap blured js-message_photo" style="left:0px;top:0px;width:453px;height:301px;margin-right:0px;margin-bottom:2px;background-image:url('https://cdn4.cdn-telegram.org/file/H6OgSicVEMK_djm09iqM-Ttc0cv0ya4lnRWdwXQiUUD-dNUq4Pz45otV1yPNLFXSCiJXJRJbkWNM4ySaF2z93S2oAsm4zwK-SCBuclFaN5Zvbm1C9nJgy6HGkODzuQrJgQopUFZbqzKbui6xjZzDF2aznDwvh0M9CApPUukpkOqNL2BPtKd1qrP3RJTZf0DHCtsHvRIxzCN5D-t91QPnC5g_Avzrmve5TEbcqzQJ4KoeLiBaSUxZjacZFISq4OXBVKsfl78cg2YEkRLwhtavjY30zV3sn4I022HsY6692wgIrU6Dso5zDkIstH_vG7nQwaru1lJOiULDI7Muqid7DQ.jpg')" data-ratio="1.5009380863039" href="https://t.me/mchs_bryansk/4656?single">
                            <div class="grouped_media_helper" style="left:0;right:0;top:76px;bottom:76px;">
                             <div class="tgme_widget_message_photo grouped_media" style="left:0;right:0;top:-1px;bottom:0px;"></div>
                            </div> </a>
                           <a class="tgme_widget_message_photo_wrap grouped_media_wrap blured js-message_photo" style="left:0px;top:303px;width:225px;height:150px;margin-right:2px;margin-bottom:0px;background-image:url('https://cdn4.cdn-telegram.org/file/NTCeU8xSNE5hxLTIRrdmH3YPOvb1S2vVbWAQ9yS8zPsGKxntM1VwxUxljry5XqTTuQZKnN_H9BOXvkRVtoF0RobvkFuUgL-8cKovQl0x9j1J-hiNm4npdmJ8ld36DkfapIZg3QuButh6ynsK0c1bFyeW_wrd3kcvcg99k7YJH6YFabQwoqvPhplHFBcf7AURKkTUzkIKMhbNhTQyUp6erZHdD7OGTBNSO8eqPIqLFkDK2t5l8I9_vTWnAMvvBmUzoAfypu3gXkK6X9_C8wU25FdKA2QY4178ykMsgwYwaip3SMhnWm4jlHguBbeunvFm5s-VjwUlaeVI9Sgy_I1KMQ.jpg')" data-ratio="1.5009380863039" href="https://t.me/mchs_bryansk/4657?single">
                            <div class="grouped_media_helper" style="left:0;right:0;top:76px;bottom:76px;">
                             <div class="tgme_widget_message_photo grouped_media" style="top:0;bottom:0;left:-1px;right:0px;"></div>
                            </div> </a>
                           <a class="tgme_widget_message_photo_wrap grouped_media_wrap blured js-message_photo" style="left:227px;top:303px;width:226px;height:150px;margin-right:0px;margin-bottom:0px;background-image:url('https://cdn4.cdn-telegram.org/file/IxMdZpPesJC8C9he0LXmFGVRqF-zJo7n8VtDD2fQqjRMRp9BbBCLSMy0VtoVzwNZaw2hSLHwh030q_2U9N5596A6bK80miX9gtv_4QPxWCrzCKh24qJV9Tgjj5FGfX8Yveg3IRAgR8Wx7ooanOdRWdQR4qU9RkST7OZxkHy5dIdTCdwxPYdDgfODidcL5JokPCehXaqjMBhstxjv5_NgUoJ8W6JJcviS6G-4xNc-KUYwnxLlgBE74URqHvqYuQ5Gy0EAviWkmFpsV5NCgXNnbu7a-LSl5pRXGPk9r171ACXubK396W1in4eagb144wI8DFaPQVvG914f6FDFPZJnng.jpg')" data-ratio="1.5009380863039" href="https://t.me/mchs_bryansk/4658?single">
                            <div class="grouped_media_helper" style="left:0;right:0;top:76px;bottom:76px;">
                             <div class="tgme_widget_message_photo grouped_media" style="left:0;right:0;top:-1px;bottom:0px;"></div>
                            </div> </a>
                          </div>
                         </div>
                        </div>
                        <div class="tgme_widget_message_text js-message_text" dir="auto">
                         <tg-emoji emoji-id="5208811735292590251">
                          <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09FA791E2808DF09F9A92.png')"><b>🧑‍🚒</b></i>
                         </tg-emoji>
                         <b>Сегодня отмечается День добровольца </b>
                         <br />
                         <br />
                         <tg-emoji emoji-id="5447545246172850059">
                          <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F938D.png')"><b>📍</b></i>
                         </tg-emoji>Дата была установлена Указом Президента Российской Федерации от 27 ноября 2017 года.
                         <br />
                         <br />
                         <tg-emoji emoji-id="5447127174056255828">
                          <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F938D.png')"><b>📍</b></i>
                         </tg-emoji>В Брянской области трудятся
                         <b>&nbsp;8423 добровольных пожарных</b>&nbsp;и спасателей, это более
                         <b>70 подразделений</b>. Действуя плечом к плечу с профессионалами, они вносят весомый вклад в дело спасения и помощи людям. Добровольцы обеспечивают безопасность удаленных населенных пунктов и организаций.
                         <br />
                         <br />
                         <tg-emoji emoji-id="5447545246172850059">
                          <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F938D.png')"><b>📍</b></i>
                         </tg-emoji>Только с начала этого года добровольцы участвовали в тушении пожаров и аварийно-спасательных работах около
                         <b>500 раз</b>,
                         <b>&nbsp;70</b>&nbsp;из которых ликвидировали самостоятельно.
                         <br />
                         <br />
                         <tg-emoji emoji-id="5325774969051818284">
                          <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/E299A5.png')"><b>♥️</b></i>
                         </tg-emoji>
                         <b>Главное управление МЧС России по Брянской области поздравляет вас с Днем добровольца (волонтера)!</b>
                         <br />
                         <br />
                         <a href="?q=%23%D0%9C%D0%A7%D0%A1%D0%BF%D0%BE%D0%91%D1%80%D1%8F%D0%BD%D1%81%D0%BA%D0%BE%D0%B9%D0%9E%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D0%B8">#МЧСпоБрянскойОбласти</a>&nbsp;
                         <br />
                         <br />
                         <tg-emoji emoji-id="5247125686841258468">
                          <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F8C90.png')"><b>🌐</b></i>
                         </tg-emoji>&nbsp;
                         <a href="https://t.me/mchs_bryansk" target="_blank" rel="noopener" onclick="return confirm('Open this link?\\n\\n'+this.href);">МЧС Брянской области в ТГ</a>
                         <br />
                         <tg-emoji emoji-id="5247016684866253308">
                          <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F8C90.png')"><b>🌐</b></i>
                         </tg-emoji>&nbsp;
                         <a href="https://vk.com/mchs32_bryansk" target="_blank" rel="noopener" onclick="return confirm('Open this link?\\n\\n'+this.href);">ГУ МЧС России по Брянской области в ВК</a>
                         <br />
                         <tg-emoji emoji-id="5247099418821275316">
                          <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F8C90.png')"><b>🌐</b></i>
                         </tg-emoji>&nbsp;
                         <a href="https://m.ok.ru/dk?st.cmd=altGroupMain&amp;amp;st.groupId=51727477244104&amp;amp;_prevCmd=userMain&amp;amp;tkn=8888&amp;amp;_cl.id=1722414864254&amp;amp;_clickLog=%5B%7B%22target%22%3A%22entity1%22%7D%2C%7B%22topicId%22%3A%22157178694350280%22%2C%22groupId%22%3A%2251727477244104%22%2C%22target%22%3A%22topicCard%22%7D%2C%7B%22feedPage%22%3A%221%22%2C%22topicId%22%3A%22157178694350280%22%2C%22feedPosition%22%3A%220%22%2C%22feedFeatures%22%3A%220d01b0000102020900650020ffff000000000000000301031200090000000001000001b0027bba5dffffffff%22%2C%22feedId%22%3A%22feedId_08010000008723fccb8500014ab50000000000187a1bd84507a1000065175d481e68%22%2C%22groupId%22%3A%2251727477244104%22%7D%2C%7B%22feedLocation%22%3A%22main%22%2C%22feedFilterId%22%3A%22101%22%2C%22offerLocation%22%3A%22FEED%22%7D%5D&amp;amp;_cl.sID=userMain" target="_blank" rel="noopener" onclick="return confirm('Open this link?\\n\\n'+this.href);">МЧС Брянской области в ОК</a>
                        </div>
                       </div>
                       <div class="media_not_supported_cont">
                        <div class="message_media_not_supported_wrap">
                         <div class="message_media_not_supported">
                          <div class="message_media_not_supported_label">
                           Please open Telegram to view this post
                          </div>
                          <a href="https://t.me/mchs_bryansk/4656?single" class="message_media_view_in_telegram">VIEW IN TELEGRAM</a>
                         </div>
                        </div>
                       </div>
                      </div>
                     </div>
                     <div class="media_not_supported_cont">
                      <div class="message_media_not_supported_wrap">
                       <div class="message_media_not_supported">
                        <div class="message_media_not_supported_label">
                         Please open Telegram to view this post
                        </div>
                        <a href="https://t.me/mchs_bryansk/4656" class="message_media_view_in_telegram">VIEW IN TELEGRAM</a>
                       </div>
                      </div>
                     </div>
                     <div class="tgme_widget_message_footer compact js-message_footer">
                      <div class="tgme_widget_message_info short js-message_info">
                       <span class="tgme_widget_message_views">4.2K</span>
                       <span class="copyonly"> views</span>
                       <span class="tgme_widget_message_meta"><a class="tgme_widget_message_date" href="https://t.me/mchs_bryansk/4656"><time datetime="2024-12-05T13:10:40+00:00" class="time">13:10</time></a></span>
                      </div>
                     </div>
                    </div>
                   </div>
                  </div>
                  <div class="tgme_widget_message_wrap js-widget_message_wrap">
                   <div class="tgme_widget_message text_not_supported_wrap js-widget_message" data-post="mchs_bryansk/4659" data-view="eyJjIjotMTc2OTY3NDQyNywicCI6NDY1OSwidCI6MTczMzQyMjQyMiwiaCI6IjRlMGU4YzVjODkyNmYyZWIxOCJ9">
                    <div class="tgme_widget_message_user">
                     <a href="https://t.me/mchs_bryansk"><i class="tgme_widget_message_user_photo bgcolor3" data-content="М"><img src="https://cdn4.cdn-telegram.org/file/Xhyac5V1Lr_RRFRmipiZTjl_-mnKDvtoUn57mebYz6Ow34cZ67A1K8N02oJTdwZahM3S3__Ily0M31QPfc1lb9M7sQOTSQRclYcZFe9Fs0gF7FR_uSrhef9Ud55b_1nwBg02I-2yMxrjrspXCfUhP12he5ku_CetG0LHcSrii98PaoC6zioIM9dw_6sbwnYXT3a5NOscrgbSYFAF18gytJv8LZzoHbQGTNVj_ZSVxDlX-YhmXy2zOmcLW_Q8O2NzCOllEXNbG-ZkvMABrZIbx-KKaihY2grESasMRtGP4u0mx_VDtjicf-ce2a1Mwxj4f7UBEwNKUVIYSQ4iXnWdWA.jpg" /></i></a>
                    </div>
                    <div class="tgme_widget_message_bubble">
                     <i class="tgme_widget_message_bubble_tail">
                      <svg class="bubble_icon" width="9px" height="20px" viewbox="0 0 9 20">
                       <g fill="none">
                        <path class="background" fill="#ffffff" d="M8,1 L9,1 L9,20 L8,20 L8,18 C7.807,15.161 7.124,12.233 5.950,9.218 C5.046,6.893 3.504,4.733 1.325,2.738 L1.325,2.738 C0.917,2.365 0.89,1.732 1.263,1.325 C1.452,1.118 1.72,1 2,1 L8,1 Z"></path>
                        <path class="border_1x" fill="#d7e3ec" d="M9,1 L2,1 C1.72,1 1.452,1.118 1.263,1.325 C0.89,1.732 0.917,2.365 1.325,2.738 C3.504,4.733 5.046,6.893 5.95,9.218 C7.124,12.233 7.807,15.161 8,18 L8,20 L9,20 L9,1 Z M2,0 L9,0 L9,20 L7,20 L7,20 L7.002,18.068 C6.816,15.333 6.156,12.504 5.018,9.58 C4.172,7.406 2.72,5.371 0.649,3.475 C-0.165,2.729 -0.221,1.464 0.525,0.649 C0.904,0.236 1.439,0 2,0 Z"></path>
                        <path class="border_2x" d="M9,1 L2,1 C1.72,1 1.452,1.118 1.263,1.325 C0.89,1.732 0.917,2.365 1.325,2.738 C3.504,4.733 5.046,6.893 5.95,9.218 C7.124,12.233 7.807,15.161 8,18 L8,20 L9,20 L9,1 Z M2,0.5 L9,0.5 L9,20 L7.5,20 L7.5,20 L7.501,18.034 C7.312,15.247 6.64,12.369 5.484,9.399 C4.609,7.15 3.112,5.052 0.987,3.106 C0.376,2.547 0.334,1.598 0.894,0.987 C1.178,0.677 1.579,0.5 2,0.5 Z"></path>
                        <path class="border_3x" d="M9,1 L2,1 C1.72,1 1.452,1.118 1.263,1.325 C0.89,1.732 0.917,2.365 1.325,2.738 C3.504,4.733 5.046,6.893 5.95,9.218 C7.124,12.233 7.807,15.161 8,18 L8,20 L9,20 L9,1 Z M2,0.667 L9,0.667 L9,20 L7.667,20 L7.667,20 L7.668,18.023 C7.477,15.218 6.802,12.324 5.64,9.338 C4.755,7.064 3.243,4.946 1.1,2.983 C0.557,2.486 0.52,1.643 1.017,1.1 C1.269,0.824 1.626,0.667 2,0.667 Z"></path>
                       </g>
                      </svg> </i>
                     <div class="tgme_widget_message_author accent_color">
                      <a class="tgme_widget_message_owner_name" href="https://t.me/mchs_bryansk"><span dir="auto">МЧС Брянской области</span></a>
                     </div>
                     <div class="media_supported_cont">
                      <a class="tgme_widget_message_photo_wrap 5373188328964352447 1251042897_456255935" href="https://t.me/mchs_bryansk/4659" style="width:800px;background-image:url('https://cdn4.cdn-telegram.org/file/UWTpQA2aewDAS5uv_iiITPPxW9oDMXS7hVAwrwA-hid42huSNT88c5oR8HS4DEcjya8ybZEgESmYd2zNMWo-dy-v4mOdlDNtNFMELuKVhHdpsk5m2FDhDqUN4xGFfuULcvfoIlDe44LTnKs_1RWhavh3MEunjhQqPC10jcXtQVxFEaK2SST9QCXQdQhHk7V0P6NSReSeiKYaw1wPAHaqJgwD1yaBNR0MNuHHfGRM9UT3stGuOyZoQuCBx7KP9cM6CddZzkdFaIg9MtiWAFbYm8UUcQNPyU2btvNQ7sj7JBbIIljGBUkx5CrcPrQ5vLF44-EFjOHGxSqSiv1gshgzFw.jpg')">
                       <div class="tgme_widget_message_photo" style="padding-top:75%"></div> </a>
                      <div class="tgme_widget_message_text js-message_text" dir="auto">
                       <tg-emoji emoji-id="5427386490751557044">
                        <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F9A93.png')"><b>🚓</b></i>
                       </tg-emoji>
                       <tg-emoji emoji-id="5427134178602786992">
                        <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F9A97.png')"><b>🚗</b></i>
                       </tg-emoji>
                       <b>Пожарно – спасательное подразделение приняло участие в ликвидации последствий ДТП в Дубровском районе </b>
                       <br />
                       <br />05.12.2024 в ОДС ГУ МЧС России по Брянской области поступило сообщение о ДТП с участием легкового и грузового автомобилей по адресу: Дубровский район, федеральная автодорога Р-120. В ликвидации последствий ДТП принимало участие пожарно-спасательное подразделение государственной противопожарной службы, личный состав которого производил смыв горюче-смазочных материалов с дорожного полотна.
                       <br />
                       <br />
                       <tg-emoji emoji-id="5435911683006542345">
                        <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F939E.png')"><b>📞</b></i>
                       </tg-emoji>Позвонить и обратиться за помощью пожарно-спасательной службы можно по телефону &laquo;
                       <tg-emoji emoji-id="5294526633842062765">
                        <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/30E283A3.png')"><b>0️⃣</b></i>
                       </tg-emoji>
                       <tg-emoji emoji-id="5294062292042790600">
                        <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/31E283A3.png')"><b>1️⃣</b></i>
                       </tg-emoji>&raquo;, с мобильного - &laquo;
                       <tg-emoji emoji-id="5294062292042790600">
                        <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/31E283A3.png')"><b>1️⃣</b></i>
                       </tg-emoji>
                       <tg-emoji emoji-id="5294526633842062765">
                        <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/30E283A3.png')"><b>0️⃣</b></i>
                       </tg-emoji>
                       <tg-emoji emoji-id="5294062292042790600">
                        <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/31E283A3.png')"><b>1️⃣</b></i>
                       </tg-emoji>&raquo;. Единый номер вызова экстренных оперативных служб – &laquo;
                       <tg-emoji emoji-id="5294062292042790600">
                        <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/31E283A3.png')"><b>1️⃣</b></i>
                       </tg-emoji>
                       <tg-emoji emoji-id="5294062292042790600">
                        <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/31E283A3.png')"><b>1️⃣</b></i>
                       </tg-emoji>
                       <tg-emoji emoji-id="5294532526537192148">
                        <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/32E283A3.png')"><b>2️⃣</b></i>
                       </tg-emoji>&raquo;.
                       <br />
                       <br />
                       <a href="?q=%23%D0%94%D0%A2%D0%9F">#ДТП</a>
                       <br />
                       <a href="?q=%23%D0%BE%D0%BF%D0%B5%D1%80%D0%B0%D1%82%D0%B8%D0%B2%D0%BD%D0%B0%D1%8F_%D0%B8%D0%BD%D1%84%D0%BE%D1%80%D0%BC%D0%B0%D1%86%D0%B8%D1%8F">#оперативная_информация</a>
                       <br />
                       <a href="?q=%23%D0%9C%D0%A7%D0%A1%D0%BF%D0%BE%D0%91%D1%80%D1%8F%D0%BD%D1%81%D0%BA%D0%BE%D0%B9%D0%9E%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D0%B8">#МЧСпоБрянскойОбласти</a>
                       <br />
                       <br />
                       <tg-emoji emoji-id="5247125686841258468">
                        <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F8C90.png')"><b>🌐</b></i>
                       </tg-emoji>
                       <a href="https://t.me/mchs_bryansk" target="_blank" rel="noopener" onclick="return confirm('Open this link?\\n\\n'+this.href);">МЧС Брянской области в ТГ</a>
                       <br />
                       <tg-emoji emoji-id="5247016684866253308">
                        <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F8C90.png')"><b>🌐</b></i>
                       </tg-emoji>
                       <a href="https://vk.com/mchs32_bryansk" target="_blank" rel="noopener" onclick="return confirm('Open this link?\\n\\n'+this.href);">ГУ МЧС России по Брянской области в ВК</a>
                       <br />
                       <tg-emoji emoji-id="5247099418821275316">
                        <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F8C90.png')"><b>🌐</b></i>
                       </tg-emoji>
                       <a href="https://m.ok.ru/dk?st.cmd=altGroupMain&amp;amp;st.groupId=51727477244104&amp;amp;_prevCmd=userMain&amp;amp;tkn=8888&amp;amp;_cl.id=1722414864254&amp;amp;_clickLog=%5B%7B%22target%22%3A%22entity1%22%7D%2C%7B%22topicId%22%3A%22157178694350280%22%2C%22groupId%22%3A%2251727477244104%22%2C%22target%22%3A%22topicCard%22%7D%2C%7B%22feedPage%22%3A%221%22%2C%22topicId%22%3A%22157178694350280%22%2C%22feedPosition%22%3A%220%22%2C%22feedFeatures%22%3A%220d01b0000102020900650020ffff000000000000000301031200090000000001000001b0027bba5dffffffff%22%2C%22feedId%22%3A%22feedId_08010000008723fccb8500014ab50000000000187a1bd84507a1000065175d481e68%22%2C%22groupId%22%3A%2251727477244104%22%7D%2C%7B%22feedLocation%22%3A%22main%22%2C%22feedFilterId%22%3A%22101%22%2C%22offerLocation%22%3A%22FEED%22%7D%5D&amp;amp;_cl.sID=userMain" target="_blank" rel="noopener" onclick="return confirm('Open this link?\\n\\n'+this.href);">МЧС Брянской области в ОК</a>
                      </div>
                     </div>
                     <div class="media_not_supported_cont">
                      <div class="message_media_not_supported_wrap">
                       <div class="message_media_not_supported">
                        <div class="message_media_not_supported_label">
                         Please open Telegram to view this post
                        </div>
                        <a href="https://t.me/mchs_bryansk/4659" class="message_media_view_in_telegram">VIEW IN TELEGRAM</a>
                       </div>
                      </div>
                     </div>
                     <div class="tgme_widget_message_footer compact js-message_footer">
                      <div class="tgme_widget_message_info short js-message_info">
                       <span class="tgme_widget_message_views">3.7K</span>
                       <span class="copyonly"> views</span>
                       <span class="tgme_widget_message_meta"><a class="tgme_widget_message_date" href="https://t.me/mchs_bryansk/4659"><time datetime="2024-12-05T13:56:25+00:00" class="time">13:56</time></a></span>
                      </div>
                     </div>
                    </div>
                   </div>
                  </div>
                  <div class="tgme_widget_message_wrap js-widget_message_wrap">
                   <div class="tgme_widget_message text_not_supported_wrap js-widget_message" data-post="mchs_bryansk/4660" data-view="eyJjIjotMTc2OTY3NDQyNywicCI6NDY2MCwidCI6MTczMzQyMjQyMiwiaCI6ImMzZDVmMmIwM2M3NGU1MDUxYSJ9">
                    <div class="tgme_widget_message_user">
                     <a href="https://t.me/mchs_bryansk"><i class="tgme_widget_message_user_photo bgcolor3" data-content="М"><img src="https://cdn4.cdn-telegram.org/file/Xhyac5V1Lr_RRFRmipiZTjl_-mnKDvtoUn57mebYz6Ow34cZ67A1K8N02oJTdwZahM3S3__Ily0M31QPfc1lb9M7sQOTSQRclYcZFe9Fs0gF7FR_uSrhef9Ud55b_1nwBg02I-2yMxrjrspXCfUhP12he5ku_CetG0LHcSrii98PaoC6zioIM9dw_6sbwnYXT3a5NOscrgbSYFAF18gytJv8LZzoHbQGTNVj_ZSVxDlX-YhmXy2zOmcLW_Q8O2NzCOllEXNbG-ZkvMABrZIbx-KKaihY2grESasMRtGP4u0mx_VDtjicf-ce2a1Mwxj4f7UBEwNKUVIYSQ4iXnWdWA.jpg" /></i></a>
                    </div>
                    <div class="tgme_widget_message_bubble">
                     <i class="tgme_widget_message_bubble_tail">
                      <svg class="bubble_icon" width="9px" height="20px" viewbox="0 0 9 20">
                       <g fill="none">
                        <path class="background" fill="#ffffff" d="M8,1 L9,1 L9,20 L8,20 L8,18 C7.807,15.161 7.124,12.233 5.950,9.218 C5.046,6.893 3.504,4.733 1.325,2.738 L1.325,2.738 C0.917,2.365 0.89,1.732 1.263,1.325 C1.452,1.118 1.72,1 2,1 L8,1 Z"></path>
                        <path class="border_1x" fill="#d7e3ec" d="M9,1 L2,1 C1.72,1 1.452,1.118 1.263,1.325 C0.89,1.732 0.917,2.365 1.325,2.738 C3.504,4.733 5.046,6.893 5.95,9.218 C7.124,12.233 7.807,15.161 8,18 L8,20 L9,20 L9,1 Z M2,0 L9,0 L9,20 L7,20 L7,20 L7.002,18.068 C6.816,15.333 6.156,12.504 5.018,9.58 C4.172,7.406 2.72,5.371 0.649,3.475 C-0.165,2.729 -0.221,1.464 0.525,0.649 C0.904,0.236 1.439,0 2,0 Z"></path>
                        <path class="border_2x" d="M9,1 L2,1 C1.72,1 1.452,1.118 1.263,1.325 C0.89,1.732 0.917,2.365 1.325,2.738 C3.504,4.733 5.046,6.893 5.95,9.218 C7.124,12.233 7.807,15.161 8,18 L8,20 L9,20 L9,1 Z M2,0.5 L9,0.5 L9,20 L7.5,20 L7.5,20 L7.501,18.034 C7.312,15.247 6.64,12.369 5.484,9.399 C4.609,7.15 3.112,5.052 0.987,3.106 C0.376,2.547 0.334,1.598 0.894,0.987 C1.178,0.677 1.579,0.5 2,0.5 Z"></path>
                        <path class="border_3x" d="M9,1 L2,1 C1.72,1 1.452,1.118 1.263,1.325 C0.89,1.732 0.917,2.365 1.325,2.738 C3.504,4.733 5.046,6.893 5.95,9.218 C7.124,12.233 7.807,15.161 8,18 L8,20 L9,20 L9,1 Z M2,0.667 L9,0.667 L9,20 L7.667,20 L7.667,20 L7.668,18.023 C7.477,15.218 6.802,12.324 5.64,9.338 C4.755,7.064 3.243,4.946 1.1,2.983 C0.557,2.486 0.52,1.643 1.017,1.1 C1.269,0.824 1.626,0.667 2,0.667 Z"></path>
                       </g>
                      </svg> </i>
                     <div class="tgme_widget_message_author accent_color">
                      <a class="tgme_widget_message_owner_name" href="https://t.me/mchs_bryansk"><span dir="auto">МЧС Брянской области</span></a>
                     </div>
                     <div class="tgme_widget_message_forwarded_from accent_color">
                      Forwarded from&nbsp;
                      <a class="tgme_widget_message_forwarded_from_name" href="https://t.me/mchs_official/24927"><span dir="auto">МЧС России</span></a>
                     </div>
                     <div class="media_supported_cont">
                      <a class="tgme_widget_message_photo_wrap 5373027658532775463 1251005488_456255015" href="https://t.me/mchs_bryansk/4660" style="width:800px;background-image:url('https://cdn4.cdn-telegram.org/file/KhdgEa_UIVQ0WAZ_pfQug10Ranc9fG1i3eF7ln8fq9nPBOSeIsIScKHFpig3f01lHfVgRU4J1XU_RPbNWJ2WlGobMXCpHWwjBvjpzgK5_KlbsjjLNZv6joxwJoghBIdqY0qSKHwGfF9Eh8jsKDR25r8KGYvb3gcOYibeYATbOQHSQwz8fOqFyKSW7wKdPqvSwNdP0W9IoEwhlV9NMepsIZFrHJ6TQtKvMeohJklDHZ3VK0a5BkVYo_t4iDVDOthYxXei3UE0GUYvdubghWZZ3kgFUQSL8rNHuSyio3dJgk-eCoWGosuhj-EwYOxUwxNuWTgDNbOVBUAPAyuM4Ex1fA.jpg')">
                       <div class="tgme_widget_message_photo" style="padding-top:55.75%"></div> </a>
                      <div class="tgme_widget_message_text js-message_text" dir="auto">
                       <tg-emoji emoji-id="5323321657962540021">
                        <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F9494.png')"><b>🔔</b></i>
                       </tg-emoji>
                       <b>Завтра на Ставрополье глава МЧС России Александр Куренков наградит победителей XVI Всероссийского фестиваля &laquo;Созвездие мужества&raquo;</b>
                       <br />&nbsp;
                       <br />На пьедестале лучшие в 43 номинациях. Мероприятие начнется в 15:00 (мск). Прямая трансляция с места событий
                       <a href="https://smotrim.ru/channel/271" target="_blank" rel="noopener" onclick="return confirm('Open this link?\\n\\n'+this.href);">ЗДЕСЬ.</a>
                       <br />
                       <br />
                       <a href="https://t.me/mchs_official" target="_blank">@mchs_official</a>
                      </div>
                     </div>
                     <div class="media_not_supported_cont">
                      <div class="message_media_not_supported_wrap">
                       <div class="message_media_not_supported">
                        <div class="message_media_not_supported_label">
                         Please open Telegram to view this post
                        </div>
                        <a href="https://t.me/mchs_bryansk/4660" class="message_media_view_in_telegram">VIEW IN TELEGRAM</a>
                       </div>
                      </div>
                     </div>
                     <div class="tgme_widget_message_footer compact js-message_footer">
                      <div class="tgme_widget_message_info short js-message_info">
                       <span class="tgme_widget_message_views">3.6K</span>
                       <span class="copyonly"> views</span>
                       <span class="tgme_widget_message_meta"><a class="tgme_widget_message_date" href="https://t.me/mchs_bryansk/4660"><time datetime="2024-12-05T14:30:04+00:00" class="time">14:30</time></a></span>
                      </div>
                     </div>
                    </div>
                   </div>
                  </div>
                  <div class="tgme_widget_message_wrap js-widget_message_wrap">
                   <div class="tgme_widget_message text_not_supported_wrap js-widget_message" data-post="mchs_bryansk/4661" data-view="eyJjIjotMTc2OTY3NDQyNywicCI6NDY2MSwidCI6MTczMzQyMjQyMiwiaCI6IjhkYmUwZTZmOWY0ZmY5Zjk0ZSJ9">
                    <div class="tgme_widget_message_user">
                     <a href="https://t.me/mchs_bryansk"><i class="tgme_widget_message_user_photo bgcolor3" data-content="М"><img src="https://cdn4.cdn-telegram.org/file/Xhyac5V1Lr_RRFRmipiZTjl_-mnKDvtoUn57mebYz6Ow34cZ67A1K8N02oJTdwZahM3S3__Ily0M31QPfc1lb9M7sQOTSQRclYcZFe9Fs0gF7FR_uSrhef9Ud55b_1nwBg02I-2yMxrjrspXCfUhP12he5ku_CetG0LHcSrii98PaoC6zioIM9dw_6sbwnYXT3a5NOscrgbSYFAF18gytJv8LZzoHbQGTNVj_ZSVxDlX-YhmXy2zOmcLW_Q8O2NzCOllEXNbG-ZkvMABrZIbx-KKaihY2grESasMRtGP4u0mx_VDtjicf-ce2a1Mwxj4f7UBEwNKUVIYSQ4iXnWdWA.jpg" /></i></a>
                    </div>
                    <div class="tgme_widget_message_bubble">
                     <i class="tgme_widget_message_bubble_tail">
                      <svg class="bubble_icon" width="9px" height="20px" viewbox="0 0 9 20">
                       <g fill="none">
                        <path class="background" fill="#ffffff" d="M8,1 L9,1 L9,20 L8,20 L8,18 C7.807,15.161 7.124,12.233 5.950,9.218 C5.046,6.893 3.504,4.733 1.325,2.738 L1.325,2.738 C0.917,2.365 0.89,1.732 1.263,1.325 C1.452,1.118 1.72,1 2,1 L8,1 Z"></path>
                        <path class="border_1x" fill="#d7e3ec" d="M9,1 L2,1 C1.72,1 1.452,1.118 1.263,1.325 C0.89,1.732 0.917,2.365 1.325,2.738 C3.504,4.733 5.046,6.893 5.95,9.218 C7.124,12.233 7.807,15.161 8,18 L8,20 L9,20 L9,1 Z M2,0 L9,0 L9,20 L7,20 L7,20 L7.002,18.068 C6.816,15.333 6.156,12.504 5.018,9.58 C4.172,7.406 2.72,5.371 0.649,3.475 C-0.165,2.729 -0.221,1.464 0.525,0.649 C0.904,0.236 1.439,0 2,0 Z"></path>
                        <path class="border_2x" d="M9,1 L2,1 C1.72,1 1.452,1.118 1.263,1.325 C0.89,1.732 0.917,2.365 1.325,2.738 C3.504,4.733 5.046,6.893 5.95,9.218 C7.124,12.233 7.807,15.161 8,18 L8,20 L9,20 L9,1 Z M2,0.5 L9,0.5 L9,20 L7.5,20 L7.5,20 L7.501,18.034 C7.312,15.247 6.64,12.369 5.484,9.399 C4.609,7.15 3.112,5.052 0.987,3.106 C0.376,2.547 0.334,1.598 0.894,0.987 C1.178,0.677 1.579,0.5 2,0.5 Z"></path>
                        <path class="border_3x" d="M9,1 L2,1 C1.72,1 1.452,1.118 1.263,1.325 C0.89,1.732 0.917,2.365 1.325,2.738 C3.504,4.733 5.046,6.893 5.95,9.218 C7.124,12.233 7.807,15.161 8,18 L8,20 L9,20 L9,1 Z M2,0.667 L9,0.667 L9,20 L7.667,20 L7.667,20 L7.668,18.023 C7.477,15.218 6.802,12.324 5.64,9.338 C4.755,7.064 3.243,4.946 1.1,2.983 C0.557,2.486 0.52,1.643 1.017,1.1 C1.269,0.824 1.626,0.667 2,0.667 Z"></path>
                       </g>
                      </svg> </i>
                     <div class="tgme_widget_message_author accent_color">
                      <a class="tgme_widget_message_owner_name" href="https://t.me/mchs_bryansk"><span dir="auto">МЧС Брянской области</span></a>
                     </div>
                     <div class="media_supported_cont">
                      <a class="tgme_widget_message_video_player not_supported js-message_video_player" href="https://t.me/mchs_bryansk/4661"><i class="tgme_widget_message_video_thumb" style="background-image:url('https://cdn4.cdn-telegram.org/file/pOQ7iY1076_Bd6OEG5hIWUIF74ggzDFU8Iigp6DXS0ztRmkqlSScleTbcCRpXQT6TSrCdaDcCPJBgnr23h6pFluLlZBR4sZnrYZmhdvN3orPQcLtzM5VE6U2fY5Q8XoE4Bl_qV_SoKoHjixveC8GJNu8e7g9sLTAx2canXEuOPPvxK942lhxghr1kt6TvZ-L6_8NFpO6aVxCWYq328NxzQ6C_BtIApxRUu4yACsIW-GkYrbTeXaMBjsDCUcWfh9Nn10mlZfzT3Y6yJ9aKm65zWhUVSPQAUumZ3a6pcPpRVufA_VfkaISCcpVDXI63q5ClDYOVlkqDYNHsXZZ86UAFg')"></i>
                       <div class="tgme_widget_message_video_wrap" style="width:1280px;padding-top:56.25%">
                       </div>
                       <div class="message_video_play js-message_video_play"></div> <time class="message_video_duration js-message_video_duration">1:16</time>
                       <div class="message_media_not_supported_wrap">
                        <div class="message_media_not_supported">
                         <div class="message_media_not_supported_label">
                          Media is too big
                         </div>
                         <span class="message_media_view_in_telegram">VIEW IN TELEGRAM</span>
                        </div>
                       </div></a>
                      <div class="tgme_widget_message_text js-message_text" dir="auto">
                       <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/E29CA8.png')"><b>✨</b></i>Ирина Гориславская стала лучшей среди психологов силовых структур
                       <br />
                       <br />Смотри тут
                       <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F9186.png')"><b>👆</b></i>
                       <br />
                       <br />
                       <a href="?q=%23%D0%9C%D0%A7%D0%A1%D0%BF%D0%BE%D0%91%D1%80%D1%8F%D0%BD%D1%81%D0%BA%D0%BE%D0%B9%D0%9E%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D0%B8">#МЧСпоБрянскойОбласти</a>
                       <br />
                       <br />
                       <tg-emoji emoji-id="5247125686841258468">
                        <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F8C90.png')"><b>🌐</b></i>
                       </tg-emoji>
                       <a href="https://t.me/mchs_bryansk" target="_blank" rel="noopener" onclick="return confirm('Open this link?\\n\\n'+this.href);">МЧС Брянской области в ТГ</a>
                       <br />
                       <tg-emoji emoji-id="5247016684866253308">
                        <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F8C90.png')"><b>🌐</b></i>
                       </tg-emoji>
                       <a href="https://vk.com/mchs32_bryansk" target="_blank" rel="noopener" onclick="return confirm('Open this link?\\n\\n'+this.href);">ГУ МЧС России по Брянской области в ВК</a>
                       <br />
                       <tg-emoji emoji-id="5247099418821275316">
                        <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F8C90.png')"><b>🌐</b></i>
                       </tg-emoji>
                       <a href="https://m.ok.ru/dk?st.cmd=altGroupMain&amp;amp;st.groupId=51727477244104&amp;amp;_prevCmd=userMain&amp;amp;tkn=8888&amp;amp;_cl.id=1722414864254&amp;amp;_clickLog=%5B%7B%22target%22%3A%22entity1%22%7D%2C%7B%22topicId%22%3A%22157178694350280%22%2C%22groupId%22%3A%2251727477244104%22%2C%22target%22%3A%22topicCard%22%7D%2C%7B%22feedPage%22%3A%221%22%2C%22topicId%22%3A%22157178694350280%22%2C%22feedPosition%22%3A%220%22%2C%22feedFeatures%22%3A%220d01b0000102020900650020ffff000000000000000301031200090000000001000001b0027bba5dffffffff%22%2C%22feedId%22%3A%22feedId_08010000008723fccb8500014ab50000000000187a1bd84507a1000065175d481e68%22%2C%22groupId%22%3A%2251727477244104%22%7D%2C%7B%22feedLocation%22%3A%22main%22%2C%22feedFilterId%22%3A%22101%22%2C%22offerLocation%22%3A%22FEED%22%7D%5D&amp;amp;_cl.sID=userMain" target="_blank" rel="noopener" onclick="return confirm('Open this link?\\n\\n'+this.href);">МЧС Брянской области в ОК</a>
                      </div>
                     </div>
                     <div class="media_not_supported_cont">
                      <div class="message_media_not_supported_wrap">
                       <div class="message_media_not_supported">
                        <div class="message_media_not_supported_label">
                         Please open Telegram to view this post
                        </div>
                        <a href="https://t.me/mchs_bryansk/4661" class="message_media_view_in_telegram">VIEW IN TELEGRAM</a>
                       </div>
                      </div>
                     </div>
                     <div class="tgme_widget_message_footer compact js-message_footer">
                      <div class="tgme_widget_message_info short js-message_info">
                       <span class="tgme_widget_message_views">3.3K</span>
                       <span class="copyonly"> views</span>
                       <span class="tgme_widget_message_meta"><a class="tgme_widget_message_date" href="https://t.me/mchs_bryansk/4661"><time datetime="2024-12-05T15:08:13+00:00" class="time">15:08</time></a></span>
                      </div>
                     </div>
                    </div>
                   </div>
                  </div>     \s
            """;
}
