
generate key in terminal using this line:
        keytool -genkey -alias myssl -keyalg RSA -keystore keystore.p12 -storetype PKCS12 -validity 3650 -keysize 2048

put these lines in application.properties:
        server.port = 8443
        # server.ssl.key-password = ***another-secret***
        # server.ssl.key-store = classpath:ssl/keystore.jks
        server.ssl.key-store = classpath:keystore.p12
        server.ssl.key-store-password = ***secret***
        server.ssl.keyStoreType = PKCS12
        server.ssl.keyAlias = myssl


pass: kEY@1d
Is CN=alone, O=msh, L=LA, ST=LA, C=US correct?
