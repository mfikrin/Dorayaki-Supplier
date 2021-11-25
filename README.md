# Dorayaki-Supplier
Interface Pabrik, Web Service dengan JAXWS dan SOAP

## Deskripsi Web Service
Dorayaki-Supplier merupakan Web Service selaku interface pabrik yang dengan JAXWS dan protokol SOAP. Dorayaki-Supplier berguna sebagai penghubung antara toko dengan pabrik, menerima request dari toko, mengirim request ke pabrik, memberikan status request ke toko, memberikan data varian nama dorayaki yang disediakan pabrik ke toko, dan melakukan rate-limiting request yang dilakukan toko. Project dideploy menggunakan Apache Tomcat v9 dan dependency project dilacak dengan Apache Maven.

## Skema Basis Data
Skema basis data yang tersedia sama dengan dorayaki-factory-server, pada dorayaki-supplier yang diakses hanya tabel dora (yang berisi daftar nama dorayaki), request(berisi request stok dorayaki yang dilakukan toko), dan request_log(berisi log dari request yang dilakukan toko).
```
dora(dora_id,dora_name)
request(request_id,dora_id,req_qty,status)
request_log(log_id,request_id,ip,timestamp_req,epoint)
```
## Endpoint WSDL, Service Method
1. `DoraSupp/ws/req?wsdl`
   - insertRequest : 
        - menerima request penambahan atau pengurangan stok dorayaki dari toko
        - melakukan rate-limit (max. 10 request dalam 5 menit terakhir untuk suatu IP Address)
        - melakukan insersi request ke tabel request dan request_log jika tidak melewati batas yang ditentukan rate-limiter
        - melakukan forwarding request ke endpoint /reqdor milik REST API backend (dorayaki-factory-server)
   - getStatus :
        - menerima permintaan status request dari toko, memberikan response yaitu status request yang dilakukan toko dengan IP Address tertentu
   
2. `DoraSupp/ws/dora?wsdl`
   - getDorayaki :
        - menerima permintaan daftar varian dorayaki dari toko, memberikan response yaitu daftar nama varian dorayaki dari basis data pabrik

## Pembagian Tugas
1. Menerima request penambahan atau pengurangan stok dorayaki dari toko : 13519018
2. Rate-limiting : 13519018
3. Insert request ke tabel request dan request_log : 13519018
4. Forward request ke endpoint /reqdor milik REST API backend : 13519018
5. Menerima  permintaan status request dari toko dan memberikan response : 13519018
6. Menerima permintaan daftar nama varian dorayaki dari toko dan memberikan response : 13519018

## Dependency
- Disarankan menggunakan [Eclipse IDE](https://www.eclipse.org/downloads/) for Enterprise Java and Web Development. Pastikan software yang berkaitan dengan Web Development sudah terinstal.
- [JDK 1.8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
- [Apache Tomcat v9](https://tomcat.apache.org/download-90.cgi) sebagai server.
- Dependency lainnya yang diatur [Maven](https://maven.apache.org/). 

## Cara Menjalankan
1. Import directory dorayaki-supplier sebagai project pada Eclipse IDE, dinamakan DoraSupp
2. Buat server Tomcat v9 yang mengandung project DoraSupp
3. Jalankan server Tomcat v9 yang sudah dibuat
4. Untuk melihat WSDL, dapat mengakses localhost:8080/[endpoint](#endpoint-wsdl-service-method)