enum class Nivel {BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(val id: Int, var nome: String) {
    val formacoesInscritas = mutableListOf<Formacao>()

    fun inscreverNaFormacao(formacao: Formacao) {
        formacoesInscritas.add(formacao)
    }
}

data class ConteudoEducacional(val nome: String, val duracao: Int, val nivel: Nivel)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {
    private val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
        usuario.inscreverNaFormacao(this)
    }

    fun exibirInformacoes() {
        println("Nome da Formação: $nome")
        println("Conteúdos da Formação:")
        for (conteudo in conteudos) {
            println(" ${conteudo.nome} (Duração: ${conteudo.duracao} minutos, Nível: ${conteudo.nivel})")
        }
        println("Usuários Inscritos na Formação:")
        for (usuario in inscritos) {
            println(" ID: ${usuario.id}, Nome: ${usuario.nome}")
        }
    }
}

fun criarConteudo(nome: String, duracao: Int, nivel: Nivel): ConteudoEducacional {
    return ConteudoEducacional(nome, duracao, nivel)
}

fun main() {
    val usuario1 = Usuario(1, "Duani")
    val usuario2 = Usuario(2, "Roberta")

    val conteudoBasico = criarConteudo("Kotlin Básico", 10, Nivel.BASICO)
    val conteudoAvancado = criarConteudo("Kotlin Avançado", 30, Nivel.AVANCADO)

    val formacaoKotlin = Formacao("Curso Completo de Kotlin", mutableListOf(conteudoBasico, conteudoAvancado))

    formacaoKotlin.matricular(usuario1)
    formacaoKotlin.matricular(usuario2)

    formacaoKotlin.exibirInformacoes()

    println("Formações Inscritas por ${usuario1.nome}:")
    for (formacao in usuario1.formacoesInscritas) {
        println(" ${formacao.nome}")
    }
    
}
