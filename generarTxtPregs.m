function generarTxtPregs (filename, sheet, xlRange, outputFileName, encoding)

[~,~,raw] = xlsread(filename,sheet,xlRange);

fin = fix((size(raw,1)+1)/6);
for k=1:fin
    for i=1:4
        if strcmp(raw{(k-1)*6+i+1,1},'*')
            correctAnswer(k)=i;
        end
    end
end   
   

count =0;
t=1;

if (strcmp(encoding, 'UTF-8'))
[textFile, message] = fopen(outputFileName , 'w', 'native', 'UTF-8');
elseif (strcmp(encoding, 'ANSI'))
[textFile, message] = fopen(outputFileName , 'w');       
end

for i =1:size(raw,1)
    if ~isnan(raw{i,2})
        if isstr(raw{i,2})
            formatSpec = '%s\n';
            fprintf(textFile, formatSpec, raw{i,2});
            count=count+1;
        else
            formatSpec = '%d\n';
            fprintf(textFile, formatSpec, raw{i,2});   
            count=count+1;
        end
        
    end
    
    if count == 5
        fprintf(textFile, '%d\n', correctAnswer(t));
        t=t+1;
        count=0;
    end
end

fclose(textFile);