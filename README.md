# JSON-Database

A simple multithreaded **client-server** application that allows clients to interact with a lightweight **JSON-based key-value store**.

---

## Features

- Store, retrieve, and delete records using JSON-formatted requests.
- Concurrent client handling using **multithreading** and **synchronization**.
- Modular design leveraging modern Java features and design patterns.

---

## Technologies & Concepts Used

- **JSON** processing with [Gson](https://github.com/google/gson)
- **Java Sockets** for client-server communication
- **Multithreading**: Threads, Executors, Locks
- **Java Collections Framework**: `List`, `Set`, `Map`
- **Design Patterns**: Command Pattern, Singleton Pattern
- **File I/O**: Reading/writing JSON to disk
- **CLI Argument Parsing** with [JCommander](http://jcommander.org/)
- **Project Management** with [Maven](https://maven.apache.org/)

---

## Build Instructions

Ensure [Maven](https://maven.apache.org/) is installed.

From the project root, run:

```bash

🏃 Run Instructions
Start the Server

java -jar server/target/server-jar-with-dependencies.jar

Send a Request from the Client
Method 1: Using Command-Line Arguments

java -jar client/target/client-jar-with-dependencies.jar -t set -k "some key" -v "some value"

Method 2: Using a JSON Input File

Create a file called set.json with the following content:

{
  "type": "set",
  "key": "some key",
  "value": "some value"
}

Run the client:

java -jar client/target/client-jar-with-dependencies.jar -in set.json

Example Output

Sent: {
  "type": "set",
  "key": "some key",
  "value": "some value"
}
Received: {
  "response": "OK"
}

📘 Client Usage Options
Option	Description
-t, --type	Type of request: set, get, or delete
-k, --key	Key of the record
-v, --value	Value to store (used with set)
-in, --input-file	Path to JSON file with the request
mvn clean package
