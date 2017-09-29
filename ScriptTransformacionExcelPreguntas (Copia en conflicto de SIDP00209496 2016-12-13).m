% Script de transfomación del Excel de preguntas

%Cargo el archivo de preguntas
filename = 'PREGUNTAS.xlsx';
sheet = 2;
xlRange = 'A6:B364';
[num,str,raw] = xlsread(filename,sheet,xlRange);


[textFile, message] = fopen('\test.txt', 'a');
formatSpec = '%s\n';
fprintf(textFile, formatSpec, raw{:,2});
fclose(textFile);