package com.ruaro.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ruaro.cursomc.domain.Categoria;
import com.ruaro.cursomc.domain.Cidade;
import com.ruaro.cursomc.domain.Cliente;
import com.ruaro.cursomc.domain.Endereco;
import com.ruaro.cursomc.domain.Estado;
import com.ruaro.cursomc.domain.Produto;
import com.ruaro.cursomc.domain.enums.TipoCliente;
import com.ruaro.cursomc.repositories.CategoriaRepository;
import com.ruaro.cursomc.repositories.CidadeRepository;
import com.ruaro.cursomc.repositories.ClienteRepository;
import com.ruaro.cursomc.repositories.EnderecoRepository;
import com.ruaro.cursomc.repositories.EstadoRepository;
import com.ruaro.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat1.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Rio de Janeiro");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "Rio de Janeiro", est2);
		Cidade c3 = new Cidade(null, "Niterói", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Diony Luiz Ruaro", "diony.ruaro@gmail.com", "03916206907", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("21974507089", "21985567391"));
		
		Endereco e1 = new Endereco(null, "Rua Silvia Pozzano", "2820", "Apto 511 Bloco CP", "Recreio", "22790671", cli1, c2);
		Endereco e2 = new Endereco(null, "Av Prefeito Sá Lessa", "621", "Galpão", "Barros Filho", "22790671", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}
}
