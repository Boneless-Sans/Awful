#!/bin/bash

# Set the source directory where your Java files are located
sourceDir="/Users/apcomputerscience/Desktop/Awful/src/"

# Set the output directory where you want to store the main JAR file
outputDir="/Users/apcomputerscience/Desktop/"

# Create a temporary directory for compiling and packaging
tempDir=$(mktemp -d)

# Compile all Java files and create the main JAR file
for file in "$sourceDir"/*.java; do
    # Extract the file name (without extension) from the path
    baseName=$(basename "$file" .java)

    # Compile the Java file
    javac -d "$tempDir" "$file"
done

# Create the main JAR file
jar cfe "$outputDir/main.jar" Launcher -C "$tempDir" .

# Clean up the temporary directory
rm -r "$tempDir"

echo "Compilation and main JAR creation complete."
