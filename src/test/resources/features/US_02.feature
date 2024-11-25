Feature:Ziyaretci Search Butonu ile Arama Yapabilmeli
  @smoke2
 Scenario Outline:Search Butonuna İstenilen Değer Girilerek Arama Yapabilme
    Given Kullanıcı "2nHaberUrl" adresine gider
    Then  Search butonuna tıklar
    And   Input box alanına "<SearchKeyword>" degerini girer ve arama yapar
    And   Kullanıcı <ResultIndex> habere tıklar ve detaylarını görüntüler

Examples:

  | SearchKeyword | ResultIndex |
  | İstanbul      | 10          |
