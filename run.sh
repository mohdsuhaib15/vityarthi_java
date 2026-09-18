#!/bin/sh
set -e
rm -rf out/classes
mkdir -p out/classes
echo "Compiling project..."
javac -d out/classes src/bank/*.java
echo "Compilation successful."
if [ "$1" = "demo" ]; then
  java -cp out/classes bank.BankApp demo
else
  java -cp out/classes bank.BankApp
fi
