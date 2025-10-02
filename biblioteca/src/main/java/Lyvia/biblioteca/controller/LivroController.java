package Lyvia.biblioteca.controller;

import Lyvia.biblioteca.model.Livro;
import Lyvia.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<Livro> listarTodos() {
        return livroService.listarTodos();
    }

    @GetMapping("/{id}")
    public Livro buscarPorId(@PathVariable Long id) {
        return livroService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    @GetMapping("/titulo/{titulo}")
    public List<Livro> buscarPorTitulo(@PathVariable String titulo) {
        return livroService.buscarPorTitulo(titulo);
    }

    @GetMapping("/autor/{autor}")
    public List<Livro> buscarPorAutor(@PathVariable String autor) {
        return livroService.buscarPorAutor(autor);
    }

    @PostMapping
    public Livro cadastrar(@RequestBody Livro livro) {
        return livroService.salvar(livro);
    }

    @PutMapping("/{id}")
    public Livro atualizar(@PathVariable Long id, @RequestBody Livro livro) {
        return livroService.atualizar(id, livro);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        livroService.excluir(id);
    }

    @PostMapping("/{id}/emprestimo")
    public Livro emprestar(@PathVariable Long id, @RequestParam String cliente) {
        return livroService.emprestar(id, cliente);
    }

    @PostMapping("/{id}/devolucao")
    public Livro devolver(@PathVariable Long id) {
        return livroService.devolver(id);
    }
}