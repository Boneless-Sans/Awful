#!/bin/bash

# Set the source directory where your Java files are located
sourceDir="/Users/apcomputerscience/Desktop/Awful/src/java/"

# Set the output directory where you want to store the main JAR file
outputDir="/Users/apcomputerscience/Desktop/output/"

# Create a temporary directory for compiling and packaging
tempDir=$(mktemp -d)

# Function to compile Java files
compile_java() {
    local source_file="$1"
    local base_name="${source_file%.java}"

    # Create the directory structure for the class file
    class_dir="${base_name#$sourceDir}"
    class_dir="${class_dir//\//.}"

    javac -d "$tempDir" "$source_file"
}

# Compile all Java files and create the main JAR file
find "$sourceDir" -name "*.java" | while read source_file; do
    compile_java "$source_file"
done

# Specify the main class with the full package and class name
mainClass="src.java.Main"

# Create the main JAR file
jar_file="$outputDir/Main.jar"
jar cfe "$jar_file" "$mainClass" -C "$tempDir" .

# Clean up the temporary directory
rm -r "$tempDir"

echo "Compilation and main JAR creation complete."

# Run the JAR file
java -jar "$jar_file"
