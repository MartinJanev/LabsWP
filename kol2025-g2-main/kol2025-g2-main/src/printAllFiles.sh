#!/bin/bash

# Output file where all contents will be saved
output_file="all_contents.txt"

# Empty the output file if it exists
> "$output_file"

# Loop through all files in the current directory and subdirectories
find . -type f | while read -r file; do
    echo "===== File: $file =====" >> "$output_file"
    cat "$file" >> "$output_file"
    echo -e "\n" >> "$output_file"
done

echo "All contents have been written to $output_file"
