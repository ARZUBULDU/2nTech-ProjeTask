Feature:Ziyaretci Yeni Bir Basvuru Yapabilmeli

  @pozitif
  Scenario Outline:Formu Başarılı Bir Şekilde Doldurma ve Gönderme İşlemi

    Given Kullanıcı "2nTechUrl" adresine gider
    And  Ad Soyad alanına "<AdSoyad>" girer
    And Doğum Tarihi alanına "<DogumTarihi>" girerim
    And T.C. Kimlik No alanına "<TcKimlikNo>" girerim
    And Cep Telefonu alanına "<CepTelefonu>" girerim
    And E-posta alanına "<Email>" girerim
    And Cv dosyasını yukler
    And Egitim seviyesini "<EgitimSeviyesi>" secer
    And Sonraki adım icin next butonuna tıklar
    And "<Pozisyon>" secer
    And Gonder butonuna tıklar

    Examples:
      | AdSoyad   | DogumTarihi | TcKimlikNo  | CepTelefonu | Email             | EgitimSeviyesi | Pozisyon      |
      | Alex John | 01.01.1975  | 99999977777 | 05222223344 | tester@tester.com | Lisans         | Test Engineer |

  @negatif
  Scenario Outline:Formu Mevcut Bilgilerle Doldurma ve Gönderme İşlemi
    Given Kullanıcı "2nTechUrl" adresine gider
    And  Ad Soyad alanına "<AdSoyad>" girer
    And Doğum Tarihi alanına "<DogumTarihi>" girerim
    And T.C. Kimlik No alanına "<TcKimlikNo>" girerim
    And Cep Telefonu alanına "<CepTelefonu>" girerim
    And E-posta alanına "<Email>" girerim
    And Cv dosyasını yukler
    And Egitim seviyesini "<EgitimSeviyesi>" secer
    And Sonraki adım icin next butonuna tıklar
    And "<Pozisyon>" secer
    And Gonder butonuna tıklar ve kayıtın yapılamadığı doğrulanır


    Examples:
      | AdSoyad   | DogumTarihi | TcKimlikNo  | CepTelefonu | Email             | EgitimSeviyesi | Pozisyon      |
      | Alex John | 01.01.1975  | 99999977777 | 05222223344 | tester@tester.com | Lisans         | Test Engineer |

