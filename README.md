# userapiwithspringboot

Merhabalar, bu repository de Spring Boot ile CRUD işlemlerini yapabileceğiniz bir rest api tasarladım. Ayrıca 
pagination işlemleri yapabilecek bir endpoint tanımladım. Bu endpoint de PagingAndSortingRepository kullandım.

Endpoints:
  GET     : /api/users *Bütün kullanıcıların bilgilerini içerir
  GET     : /api/users/{id} *Verilen id bilgisine göre o id'ye ait kişi bilgisi içerir
  POST    : /api/users *Kullanıcı oluşturmak
  PUT     : /api/users/{id} *Verilen id bilgisine göre o id'ye ait kişi bilgilerini günceller
  DELETE  : /api/users/{id} *Verilen id bilgisine göre o id'ye ait kişiyi siler
  
 Bu projeyi clone ettikten sonra Eclipse yada Intellij IDEA üzerinde açabilirsiniz. Ben Intelij İDEA kullanıyorum.
 Projeyi IDE üzerinde açtıktan sonra pom.xml dosyasındaki kütüphaneleri IDE otamatik olarak yükleyecektir.
 Gerekli kütüphaneler kurulduktan sonra src/main/resources/application.properties dosyası içerisinde database işlemlerini
 yapabilmemiz için gerekli değişiklikleri yapmalıyız. Ben database olarak postgresql kullandım. 
 
 Öncelikle eğer farklı bir database kullanmak isterseniz pom.xml içerisindeki postgresql jdbc driver'ını kullanmak istediğiniz
 database driver'ının dependency bilgisini tanımlamanız gerekmektedir. 
 
 Benim makinemde postgresql kurulu bu yüzden anlatımıma postgresql üzerinden devam edeceğim.
 
 Terminali açıp, sudo -i -u postgres yazdıktan sonra postgresql bağlanıyoruz. Daha sonra psql yazarak postgresql interaktif 
 terminale giriş yapıyoruz. Artık database oluşturma işlemlerini yapabilir ve oluşturduğumuz database'leri görebiliriz. 
 
 CREATE DATABASE users; diyerek users isimli database'i oluşturuyoruz.
 
 \l yazarak oluşmuş database'leri görebiliriz.
 
 Son olarak oluştuduğumuz database adını application.properties içersinde tanımlayıyoruz. Eğer username ve password bilgisi
 var ise onları tanımlamak gerekmektedir.
 
  spring.datasource.url= jdbc:postgresql://localhost:5432/users
  spring.datasource.username=xxxx
  spring.datasource.password= xxxxxx
  
Bu işlemlerden sonra IDE üzerinde UserapiApplication.java isimli class'ı açtıktan sonra IDE üzerinden çalıştırdığınız zaman
database ile bağlantınız kurulacak ve Entity'leriniz database içerisinde otamatik bir şekilde tabloları ve tabloadaki
sütunları oluşturacaktır. Geriye kalan endpointleri test etmek. 

Umarım anlattıklarım yardımcı olmuştur. Herkese bol kodlu günler dilerim.
 

