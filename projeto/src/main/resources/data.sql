
INSERT INTO subject (name) VALUES
    ('ADMINISTRAÇÃO'),
    ('ADMINISTRAÇÃO DE SISTEMAS'),
    ('ADMINISTRAÇÃO DE SISTEMAS DE INFORMAÇÃO'),
    ('ALGEBRA LINEAR I'),
    ('ALGORITMOS AVANÇADOS I'),
    ('ALGORITMOS AVANCADOS II'),
    ('ALGORITMOS AVANCADOS III'),
    ('ANÁLISE DE SISTEMAS'),
    ('ANÁLISE E TÉCNICAS DE ALGORITMOS'),
    ('APLICAÇÕES DE GRAFOS'),
    ('APLICAÇÕES DE PLP'),
    ('AVALIAÇÃO DE DESEMPENHO DE SISTEMAS DISCRETOS'),
    ('BANCO DE DADOS 1'),
    ('BANCO DE DADOS 2'),
    ('BASQUETEBOL FEM'),
    ('BASQUETEBOL MAS'),
    ('CALCULO DIFERENCIAL E INTEGRAL I'),
    ('CALCULO DIFERENCIAL E INTEGRAL II'),
    ('CÁLCULO NUMÉRICO'),
    ('CIÊNCIA DE DADOS DESCRITIVA'),
    ('CIÊNCIA DE DADOS PREDITIVA'),
    ('COMPILADORES'),
    ('DESENVOLVIMENTO DE APLICAÇÕES CORPORATIVAS AVANÇADAS'),
    ('DIREITO E CIDADANIA'),
    ('ECONOMIA'),
    ('ECONOMIA DE TECNOLOGIA DA INFORMAÇÃO'),
    ('EMPREENDEDORISMO'),
    ('EMPREENDEDORISMO EM SOFTWARE'),
    ('ENGENHARIA DE SOFTWARE'),
    ('ESTÁGIO INTEGRADO'),
    ('ESTÁGIO INTEGRADO I'),
    ('ESTAGIO INTEGRADO II'),
    ('ESTAGIO INTEGRADO III'),
    ('ESTATÍSTICA APLICADA'),
    ('ESTRUTURA DE DADOS'),
    ('FUNDAMENTOS DE MATEMATICA PARA CIÊNCIA DA COMPUTACAO I'),
    ('FUNDAMENTOS DE MATEMATICA PARA CIÊNCIA DA COMPUTACAO II'),
    ('FUNDAMENTOS DE PROGRAMAÇÃO CONCORRENTE'),
    ('FUTSAL'),
    ('GERÊNCIA DE REDES DE COMPUTADORES'),
    ('GESTÃO DE PROJETOS'),
    ('GESTÃO DE SISTEMAS DE INFORMAÇÃO'),
    ('GOVERNÂNCIA DA INTERNET'),
    ('INFORMÁTICA E SOCIEDADE'),
    ('INGLÊS'),
    ('INTELIGÊNCIA ARTIFICIAL'),
    ('INTERCONEXÃO DE REDES DE COMPUTADORES'),
    ('INTRODUÇÃO A BANCO DE DADOS E MINERAÇÃO DE DADOS'),
    ('INTRODUÇÃO À CIÊNCIA DA COMPUTAÇÃO'),
    ('INTRODUÇÃO À COMPUTAÇÃO'),
    ('INTRODUÇÃO À PROBABILIDADE'),
    ('JOGOS DIGITAIS'),
    ('LABORATÓRIO DE ENGENHARIA DE SOFTWARE'),
    ('LABORATÓRIO DE ESTRUTURA DE DADOS'),
    ('LABORATÓRIO DE INTERCONEXÃO DE REDES DE COMPUTADORES'),
    ('LABORATÓRIO DE ORGANIZAÇÃO E ARQUITETURA DE COMPUTADORES'),
    ('LABORATÓRIO DE PROGRAMAÇÃO 1'),
    ('LABORATÓRIO DE PROGRAMAÇÃO 2'),
    ('LEITURA E PRODUÇÃO DE TEXTO'),
    ('LÍNGUA PORTUGUESA'),
    ('LÓGICA PARA COMPUTAÇÃO'),
    ('METODOLOGIA CIENTÍFICA'),
    ('MÉTODOS E SOFTWARE NUMÉRICOS'),
    ('MÉTODOS ESTATÍSTICOS'),
    ('ORGANIZAÇÃO E ARQUITETURA DE COMPUTADORES'),
    ('PARADIGMAS DE LINGUAGENS DE PROGRAMAÇÃO'),
    ('PERCEPÇÃO COMPUTACIONAL'),
    ('PRÁTICA DE ENSINO DE COMPUTAÇÃO I'),
    ('PRÁTICA DE ENSINO DE COMPUTAÇÃO II'),
    ('PRINCÍPIOS DE DESENVOLVIMENTO WEB'),
    ('PROGRAMAÇÃO 1'),
    ('PROGRAMAÇÃO 2'),
    ('PROGRAMAÇÃO CONCORRENTE'),
    ('PROGRAMAÇÃO FUNCIONAL'),
    ('PROJETO DE REDES DE COMPUTADORES'),
    ('PROJETO DE SISTEMAS OPERACIONAIS'),
    ('PROJETO DE SOFTWARE'),
    ('PROJETO DE TRABALHO DE CONCLUSAO DE CURSO'),
    ('PROJETO EM COMPUTAÇÃO 1'),
    ('PROJETO EM COMPUTAÇÃO 2'),
    ('RECUPERAÇÃO DA INFORMAÇÃO E BUSCA NA WEB'),
    ('REDES DE COMPUTADORES'),
    ('SEMINÁRIOS'),
    ('SEMINÁRIOS (EDUCAÇÃO AMBIENTAL)'),
    ('SISTEMAS DE APOIO À DECISÃO'),
    ('SISTEMAS DE INFORMAÇÃO II'),
    ('SISTEMAS DE RECUPUPERAÇÃO DA INFORMAÇÃO'),
    ('SISTEMAS OPERACIONAIS'),
    ('SOCIOLOGIA INDUSTRIAL I'),
    ('TÉCNICAS DE PROGRAMAÇÃO'),
    ('TEORIA DA COMPUTAÇÃO'),
    ('TEORIA DOS GRAFOS'),
    ('TRABALHO DE CONCLUSAO DE CURSO'),
    ('VERIFICACAO E VALIDAÇÃO DE SOFTWARE'),
    ('VISÃO COMPUTACIONAL'),
    ('VISUALIZAÇÃO DE DADOS');

insert into user values('a.kleber.d@gmail.com', 'Anderson', 'Dantas', '123');
insert into user values('joaom@gmail.com', 'JMarcos', 'Lima', '123');
insert into comment(author_email, content, subject_id) values
    ('a.kleber.d@gmail.com', 'Trivial essa budega... reprovado', 7),
    ('a.kleber.d@gmail.com', 'While(true) purint', 7),
    ('a.kleber.d@gmail.com', 'While(true) purint', 7),
    ('a.kleber.d@gmail.com', 'While(true) purint', 7),
    ('a.kleber.d@gmail.com', 'While(true) purint', 7),
    ('a.kleber.d@gmail.com', 'While(true) purint', 7),
    ('a.kleber.d@gmail.com', 'While(true) purint', 7),
    ('a.kleber.d@gmail.com', 'While(true) purint', 7),
    ('a.kleber.d@gmail.com', 'While(true) purint', 7),
    ('a.kleber.d@gmail.com', 'While(true) purint', 7),
    ('a.kleber.d@gmail.com', 'While(true) purint', 7),
    ('a.kleber.d@gmail.com', 'While(true) purint', 7),
    ('a.kleber.d@gmail.com', 'While(true) purint', 7),
    ('a.kleber.d@gmail.com', 'While(true) purint', 7),
    ('a.kleber.d@gmail.com', 'While(true) purint', 7),
    ('a.kleber.d@gmail.com', 'While(true) purint', 7),
    ('a.kleber.d@gmail.com', 'While(true) purint', 7),
    ('a.kleber.d@gmail.com', 'While(true) purint', 7),
    ('a.kleber.d@gmail.com', 'While(true) purint', 7);
insert into comment(author_email, content, subject_id, answer_to)
    values('joaom@gmail.com', 'desclassificado', 7, 2);