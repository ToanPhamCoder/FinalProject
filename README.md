file application.properties
# Name project
spring.application.name=EcommerceShop
# Database connection properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=Dvduong9
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
jwt.secret=123456
spring.jpa.hibernate.ddl-auto=update // dùng để tự tạo bảng trong DB, nếu = none thì không tự tạo bảng trong DB
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
server.error.include-message=always
