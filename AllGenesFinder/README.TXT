AllGenesFinder
2015-10-23

Assignment

This assignment is to find all the genes in a DNA string. We will specify the algorithm that defines a gene, which is different than the algorithm we used to find a single gene in the last lesson. You will start with code from the lesson and modify it to solve this problem. You should write and test each method on small data before moving on to the next step, finding all the genes in small strings of DNA to help you debug your program.

Algorithm to identify multiple genes in a strand of DNA

To find the first gene, find the start codon ATG.
Next look immediately past ATG for the first occurrence of each of the three stop codons TAG, TGA, and TAA.
If the length of the substring between ATG and any of these three stop codons is a multiple of three, then a candidate for a gene is the start codon through the end of the stop codon.
If there is more than one valid candidate, the smallest such string is the gene. The gene includes the start and stop codon.
If no start codon was found, then you are done.
If a start codon was found, but no gene was found, then start searching for another gene via the next occurrence of a start codon starting immediately after the start codon that didn't yield a gene.
If a gene was found, then start searching for the next gene immediately after this found gene.