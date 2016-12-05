AMFCore Utils Pinning
=====================
Setups pinning for certificates you provide, for using on all AGS projects. This utility uses [OkHttp] (http://square.github.io/okhttp/)
library but you can add more clients.

More info about pinning [OWASP](https://www.owasp.org/index.php/Certificate_and_Public_Key_Pinning)



Introduction
------------
There are three different pinning configurations:

- X.509 PEM format files with '.cer' extension.
- Pairs 'host/key hash' file (one file) with '.hash' extension.
- Public key string files with '.key' extension.

You can setup one or more configurations.



Getting Started
---------------
- You need to add your certificates downloaded as X.509 PEM format with '.cer' extension, it is
preferably to add one certificate in each file for a better understandability.
    * How it works:
        * Only setted certificates work, otherwise fails.

- For adding your pairs 'host/key hash' uses a file with the same format than java property files
but with '.hash' extension. The key is the 'host' and the value is the 'hash'. Only one host is
supported.
    * How it works:
        * All pairs are checked.
        * Hosts not added here works but they are not checked.


- For adding your public keys extract it from a certificate and paste on a '.key' file. It is
preferably to add one key in each file for a better understandability.
    * How it works:
        * Only setted keys work, otherwise fails.

Finally add this files on a folder called 'pinning' on your app assets folder.

And that's all! You will have pinning.



Obtaining certificate
---------------------
You can download it clicking on green lock of your web explorer (Firefox).



Obtaining hashes
----------------
The easiest way to do it is make a call with the sample to the server with the public key pinning
configured with any public key. The logcat will show an error and will show the right key, use that.
[For more information](http://square.github.io/okhttp/javadoc/com/squareup/okhttp/CertificatePinner.html)



Obtaining public key
--------------------
<code>
openssl x509 -pubkey -noout -in pass_axa.cer > pass_axa.key
</code>



TODO
----
Add 'host/key hash' configuration more than one host support.
