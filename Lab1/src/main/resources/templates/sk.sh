#!/bin/bash

# Script to print the content of all files in the current directory and its subdirectories

# Find all files and iterate through them
find . -type f | while read -r file; do
    echo "--- Content of $file ---" >> output.txt
    cat "$file" >> output.txt
    echo "-------------------------" >> output.txt
done
