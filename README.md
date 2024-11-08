# LZW Compression and Decompression

This project implements the **Lempel-Ziv-Welch (LZW)** algorithm for data compression and decompression in Java. It includes methods to read and write files, compress a string, and decompress a compressed list of codes back to the original string.

## Table of Contents
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Example Files](#example-files)
- [Explanation of Methods](#explanation-of-methods)
- [License](#license)

## Features
- Compresses text files using the LZW algorithm, producing a series of integer codes.
- Decompresses a series of LZW integer codes back into the original text.
- Reads input from files and writes both compressed and decompressed data to separate files.

## Installation
1. **Clone the repository**:
    ```bash
    git clone https://github.com/yourusername/LZW-Compression.git
    cd LZW-Compression
    ```

2. **Open the project in IntelliJ IDEA** (or another Java IDE) and configure it as a Java project.

3. **Compile the project** in your IDE.

## Usage
1. **Prepare your input file**:
   - Create a file named `input.txt` in the project root directory.
   - Place the text you want to compress in `input.txt`.

2. **Run the main method**:
   - In your IDE, run the `main` method in `LZW.java`.

3. **Check output files**:
   - After running, the project will generate two files:
     - `compressed.txt`: Contains the compressed integer codes.
     - `decompressed.txt`: Contains the decompressed text.

### Command-Line (Alternative)
Alternatively, if you have compiled the project as a JAR file, you can run it from the command line:
```bash
java -jar LZWCompression.jar
