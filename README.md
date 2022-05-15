# clinica-odonto

# PlantUML Editor
1. Universidade Estadual do Tocantins
2. Aluno: George Daniel Marques Borges

@startuml


left to right direction



class Pessoa {
- id : Integer
- nome : String
}

class Usuario{
- senha : String
}

enum TipoUsuario{
+ ADMINISTRADOR : int
+ ATENDENTE : int
}


class PessoaJuridica{
- cnpj : String
- insricaoEstadual : String
}

class PessoaFisica{
- cpf : String
- dataNascimento : Date
}

class Clinica{

}

class Paciente{
- id : Integer
- cartaoSus : String

}

class Dentista {
- id : Integer
- cargaHoraria : int
- salario : double
}

class Telefone{
- id : Integer
- codigoArea : String
- numero : String 
}

class Endereco{
- id : Integer
- endereco : String
- numero : int
- bairro : String
- cep : String
}

class TipoAtendimento{
- id : Integer
- descricao : String
- valor : double
  
}

class Recepcao{
- id : Integer
- data : Date
}

enum Sexo{
+ MASCULINO : int
+ FEMININO : int
}

class Cidade {
- id : Integer
- nome : String
}

enum Estado{
+ TO : int
+ SP : int
+ RJ : int
+ BR : int

}


Recepcao --> Usuario
Usuario "*" *--> "1" TipoUsuario
Pessoa <|-- Paciente
Pessoa <|-- Dentista
Pessoa <|-- PessoaFisica
Pessoa <|-- PessoaJuridica
Pessoa "*" o--> "1" Sexo
Pessoa "1" o--> "*" Telefone
Pessoa "1" o--> "1" Endereco
Clinica --> PessoaJuridica
Endereco "*" o--> "1" Cidade
Cidade "*" --> "1" Estado
Recepcao "*" o--> "1" Dentista
Recepcao "*" o--> "1" Paciente
Recepcao "*" *--> "*" TipoAtendimento




@enduml
