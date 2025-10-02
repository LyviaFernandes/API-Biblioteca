package Lyvia.biblioteca.service;

import Lyvia.biblioteca.model.Livro;
import Lyvia.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Optional<Livro> buscarPorId(Long id) {
        return livroRepository.findById(id);
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Livro> buscarPorAutor(String autor) {
        return livroRepository.findByAutorContainingIgnoreCase(autor);
    }

    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    public Livro atualizar(Long id, Livro livroAtualizado) {
        return livroRepository.findById(id).map(livro -> {
            livro.setTitulo(livroAtualizado.getTitulo());
            livro.setAutor(livroAtualizado.getAutor());
            livro.setAnopublicacao(livroAtualizado.getAnopublicacao());
            return livroRepository.save(livro);
        }).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public void excluir(Long id) {
        livroRepository.deleteById(id);
    }

    public Livro emprestar(Long id, String cliente) {
        return livroRepository.findById(id).map(livro -> {
            if ("sim".equalsIgnoreCase(livro.getEmprestado())) {
                throw new RuntimeException("Livro já emprestado!");
            }
            livro.setEmprestado("sim");
            livro.setCliente(cliente);
            return livroRepository.save(livro);
        }).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public Livro devolver(Long id) {
        return livroRepository.findById(id).map(livro -> {
            livro.setEmprestado("nao");
            livro.setCliente(null);
            return livroRepository.save(livro);
        }).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }
}