package com.projeto.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projeto.cursomc.domain.Categoria;
import com.projeto.cursomc.domain.Cidade;
import com.projeto.cursomc.domain.Cliente;
import com.projeto.cursomc.domain.Endereco;
import com.projeto.cursomc.domain.Estado;
import com.projeto.cursomc.domain.Pagamento;
import com.projeto.cursomc.domain.PagamentoComBoleto;
import com.projeto.cursomc.domain.PagamentoComCartao;
import com.projeto.cursomc.domain.Pedido;
import com.projeto.cursomc.domain.Produto;
import com.projeto.cursomc.domain.enums.EstadoPagamento;
import com.projeto.cursomc.domain.enums.TipoCliente;
import com.projeto.cursomc.repositories.CategoriaRepository;
import com.projeto.cursomc.repositories.CidadeRepository;
import com.projeto.cursomc.repositories.ClienteRepository;
import com.projeto.cursomc.repositories.EnderecoRepository;
import com.projeto.cursomc.repositories.EstadoRepository;
import com.projeto.cursomc.repositories.PagamentoRepository;
import com.projeto.cursomc.repositories.PedidoRepository;
import com.projeto.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository  categoriaRepository;
	
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

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
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.save(Arrays.asList(cat1, cat2));				
		produtoRepository.save(Arrays.asList(p1, p2, p3));	
		
		Estado est1 = new Estado(null, "Alagoas");
		Estado est2 = new Estado(null, "Paraíba");
		
		Cidade c1 = new Cidade(null, "Penedo", est1);
		Cidade c2 = new Cidade(null, "Maceió", est1);
		Cidade c3 = new Cidade(null, "João Pessoa", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1, c2));
		est2.getCidades().addAll(Arrays.asList(c3));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Rafael", "raphab33@hotmail.com", "084.206.984-40", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("(83) 99895-4164", "(83)99899-9999"));
		
		Endereco e1 = new Endereco(null, "R. Sebastiao da silva Leal", "104", "Proximo ao colegio Militar", 
				                   "Mangabeira VII", "58058-840", cli1, c3);
		Endereco e2 = new Endereco(null, "Conj. Marisa leticia", "15", "Sem Complemento", 
                                   "Dom. Constatino", "57200-000", cli1, c1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1, e2));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 12:45"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, null, sdf.parse("20/10/2017 00:00"));
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pagto1, pagto2));
		
		
			
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
