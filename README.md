# Secure-Doctor-Application-Using-Client-Server-Architecture
The Secure Doctor Application is a client-server based system designed to provide a secure platform for doctors to store and manage patient files.
The application utilizes client-server architecture, where the client authenticates using username and password, and then gains access to options such as uploading files to the server, deleting files from the server (with OTP authentication), and accessing existing files stored on the server.
All files added to the server are stored in an encrypted manner using the AES encryption algorithm to ensure data security and confidentiality.


Key Features used in this project:
  - User Authentication:
      >The client-side application will prompt the user (doctor) for a username and password to gain access to the system.
      >Authentication credentials will be securely transmitted to the server for verification.
      >Access to the application's features will be granted upon successful authentication.
  - File Upload:
      >Authenticated users can upload patient files (e.g., medical records, reports) to the server through the client application.
      >Before uploading, the client will encrypt the file using AES encryption to ensure data privacy during transmission and storage.
      >The server will store the encrypted file along with relevant metadata (e.g., file name, date, owner) for easy retrieval.
  - File Deletion (OTP-Authenticated):
      >To delete a file from the server, an additional layer of security is implemented using OTP (One-Time Password) authentication.
      >The client will request OTP verification for deletion.
      >Upon receiving the OTP, the client will provide it to the server for validation before proceeding with the deletion.
  - File Access (OTP-Authenticated):
      >Accessing an existing file on the server requires OTP authentication as well.
      >The client initiates the access request and receives the OTP to proceed with decryption and viewing of the file content.
  - Data Encryption (AES Algorithm):
      >All files uploaded to the server are encrypted using the AES (Advanced Encryption Standard) algorithm, ensuring data confidentiality.
      >The client-side application will encrypt the files before transmitting them to the server.
      >The server will store the encrypted files and decrypt them when authorized clients request access.
  - Secure Communication:
      >The communication using client and server is implemented using socket programming in Java.


Technical Overview:
  - Both the client side and the server side is implemented using Java Swing and AWT(Abstract Window Toolkit)
  - The encryption is implemented in client side.
  - The communication is done using socket programming.
  - The OTP is generated randomly and is controlled using in-built Java Timer class.


Further enhancement Ideas:
- Database integration in the server side for longer access and maintain logs.
- Extend the project to a multi client architecture so that multiple clients can access the server simultaneously.
