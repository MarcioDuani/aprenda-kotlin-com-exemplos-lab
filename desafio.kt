enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(val id: Int, var nome: String) {
    val formacoesMatriculadas = mutableListOf<Formacao>()

    fun matricularNaFormacao(formacao: Formacao) {
        formacoesMatriculadas.add(formacao)
    }
}

data class ConteudoEducacional(val nome: String, val duracao: Int, val nivel: Nivel)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {
    private val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
        usuario.matricularNaFormacao(this)
    }

    fun exibirInformacoes() {
        println("Nome da Formação: $nome")
        println("Conteúdos da Formação:")
        for (conteudo in conteudos) {
            println("- ${conteudo.nome} (Duração: ${conteudo.duracao} minutos, Nível: ${conteudo.nivel})")
        }
        println("Usuários Inscritos na Formação:")
        for (usuario in inscritos) {
            println("- ID: ${usuario.id}, Nome: ${usuario.nome}")
        }
    }
}

fun criarConteudo(nome: String, duracao: Int, nivel: Nivel): ConteudoEducacional {
    return ConteudoEducacional(nome, duracao, nivel)
}

fun main() {
    val usuario1 = Usuario(1, "Márcio Duani")
    val usuario2 = Usuario(2, "Roberta Cardoso")

    val conteudoBasico = criarConteudo("Kotlin Básico", 10, Nivel.BASICO)
    val conteudoAvancado = criarConteudo("Kotlin Avançado", 30, Nivel.AVANCADO)

    val formacaoKotlinBasico = Formacao("Formação Kotlin Básico", mutableListOf(conteudoBasico))
    val formacaoKotlinAvancado = Formacao("Formação Kotlin Avançado", mutableListOf(conteudoAvancado))
    
    formacaoKotlinBasico.matricular(usuario1)
    formacaoKotlinAvancado.matricular(usuario2)

    usuario1.matricularNaFormacao(formacaoKotlinBasico)
    usuario2.matricularNaFormacao(formacaoKotlinAvancado)

    formacaoKotlinBasico.exibirInformacoes()
    formacaoKotlinAvancado.exibirInformacoes()

    println("Formações Matriculadas por ${usuario1.nome}:")
    for (formacao in usuario1.formacoesMatriculadas) {
        println("- ${formacao.nome}")
    }

    println("Formações Matriculadas por ${usuario2.nome}:")
    for (formacao in usuario2.formacoesMatriculadas) {
        println("- ${formacao.nome}")
    }
}
