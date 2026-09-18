#!/bin/sh
set -e
rm -rf out/test-classes
mkdir -p out/test-classes
echo "Running validation tests..."
javac -d out/test-classes src/bank/*.java
java -cp out/test-classes bank.ValidationTest
