% Script de transfomación del Excel de preguntas

clear all

%Cargo el archivo de preguntas
 filename = 'PREGUNTAS Revision.xlsx';
% sheet = 6;
% xlRange = 'A4:B362';
% outputFileName = 'android\assets\mobilEasyQuestions.txt';


% Mobilidad - Facil
generarTxtPregs(filename, 6, 'A4:B362', 'android\assets\questions\UTF-8 Encoding\mobilEasyQuestions.txt','UTF-8')
generarTxtPregs(filename, 6, 'A4:B362', 'android\assets\questions\ANSI Encoding\mobilEasyQuestions.txt','ANSI')

% Mobilidad - Dificil
generarTxtPregs(filename, 6, 'E4:F182', 'android\assets\questions\UTF-8 Encoding\mobilHardQuestions.txt','UTF-8')
generarTxtPregs(filename, 6, 'E4:F182', 'android\assets\questions\ANSI Encoding\mobilHardQuestions.txt','ANSI')

    
% Energia - Facil
generarTxtPregs(filename, 3, 'A4:B374', 'android\assets\questions\UTF-8 Encoding\energyEasyQuestions.txt','UTF-8')
generarTxtPregs(filename, 3, 'A4:B374', 'android\assets\questions\ANSI Encoding\energyEasyQuestions.txt','ANSI')

% Energia - Dificil
generarTxtPregs(filename, 3, 'E4:F182', 'android\assets\questions\UTF-8 Encoding\energyHardQuestions.txt','UTF-8')
generarTxtPregs(filename, 3, 'E4:F182', 'android\assets\questions\ANSI Encoding\energyHardQuestions.txt','ANSI')
 
% Agua - Facil
generarTxtPregs(filename, 2, 'A4:B362', 'android\assets\questions\UTF-8 Encoding\waterEasyQuestions.txt','UTF-8')
generarTxtPregs(filename, 2, 'A4:B362', 'android\assets\questions\ANSI Encoding\waterEasyQuestions.txt','ANSI')

% Agua - Dificil
generarTxtPregs(filename, 2, 'E4:F182', 'android\assets\questions\UTF-8 Encoding\waterHardQuestions.txt','UTF-8')
generarTxtPregs(filename, 2, 'E4:F182', 'android\assets\questions\ANSI Encoding\waterHardQuestions.txt','ANSI')

% Biodiv - Facil
generarTxtPregs(filename, 4, 'A4:B386', 'android\assets\questions\UTF-8 Encoding\biodivEasyQuestions.txt','UTF-8')
generarTxtPregs(filename, 4, 'A4:B386', 'android\assets\questions\ANSI Encoding\biodivEasyQuestions.txt','ANSI')

% Biodiv - Dificil
generarTxtPregs(filename, 4, 'E4:F242', 'android\assets\questions\UTF-8 Encoding\biodivHardQuestions.txt','UTF-8')
generarTxtPregs(filename, 4, 'E4:F242', 'android\assets\questions\ANSI Encoding\biodivHardQuestions.txt','ANSI')
                          
% Consumo - Facil
generarTxtPregs(filename, 5, 'A4:B362', 'android\assets\questions\UTF-8 Encoding\consumEasyQuestions.txt','UTF-8')
generarTxtPregs(filename, 5, 'A4:B362', 'android\assets\questions\ANSI Encoding\consumEasyQuestions.txt','ANSI')
                          
% Consumo - Dificil
generarTxtPregs(filename, 5, 'E4:F212', 'android\assets\questions\UTF-8 Encoding\consumHardQuestions.txt','UTF-8')
generarTxtPregs(filename, 5, 'E4:F212', 'android\assets\questions\ANSI Encoding\consumHardQuestions.txt','ANSI')
                          
% Tierra
generarTxtPregs(filename, 1, 'A4:B314', 'android\assets\questions\UTF-8 Encoding\earthQuestions.txt','UTF-8')
generarTxtPregs(filename, 1, 'A4:B314', 'android\assets\questions\ANSI Encoding\earthQuestions.txt','ANSI')

% Cuaderno
generarTxtPregs(filename, 7, 'A4:B170', 'android\assets\questions\UTF-8 Encoding\cuadernoQuestions.txt','UTF-8')
generarTxtPregs(filename, 7, 'A4:B170', 'android\assets\questions\ANSI Encoding\cuadernoQuestions.txt','ANSI')
     