import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.thoughtworks.xstream.XStream;

import model.Cliente;
import model.Item;
import model.Pedido;
import model.Produto;
import model.Vendedor;
import util.HibernateUtil;
import util.JsonUtils;

public class Main {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory();
		session.beginTransaction();
		
		try {

			/* CRIA VENDEDOR */
			Vendedor vendedor = criaVendedor(session);

			/* CRIA CLIENTE */
			Cliente cliente = criaCliente(session);

			/* CRIA PRODUTOS */
			Produto lapis = new Produto();
			lapis.setDescricao("LAPIS");
			lapis.setValor(new BigDecimal("0.25"));

			session.save(lapis);

			Produto caneta = new Produto();
			caneta.setDescricao("CANETA");
			caneta.setValor(new BigDecimal("0.50"));

			session.save(caneta);

			Produto borracha = new Produto();
			borracha.setDescricao("BORRACHA");
			borracha.setValor(new BigDecimal("0.25"));

			session.save(borracha);

			Produto estojo = new Produto();
			estojo.setDescricao("ESTOJO");
			estojo.setValor(new BigDecimal("4.26"));

			session.save(estojo);

			Produto caderno = new Produto();
			caderno.setDescricao("CADERNO");
			caderno.setValor(new BigDecimal("2.50"));

			session.save(caderno);

			/* CRIAR PEDIDO */
			Pedido pedido = new Pedido();
			pedido.setCliente(cliente);
			pedido.setVendedor(vendedor);

			/* lista de itens */
			List<Item> listaItens = new ArrayList<Item>();
			Item item1 = new Item();
			item1.setPedido(pedido);
			item1.setProduto(caderno);
			item1.setQuantidade(10L);
			item1.setValorTotal(caderno.getValor().multiply(new BigDecimal(item1.getQuantidade().longValue())));
			listaItens.add(item1);

			Item item2 = new Item();
			item2.setPedido(pedido);
			item2.setProduto(borracha);
			item2.setQuantidade(100L);
			item2.setValorTotal(borracha.getValor().multiply(new BigDecimal(item2.getQuantidade().longValue())));
			listaItens.add(item2);

			pedido.setListaitens(listaItens);
			pedido.setValorTotalPedido(BigDecimal.ZERO);

			for (Item item : listaItens) {
				pedido.setValorTotalPedido(pedido.getValorTotalPedido().add(item.getValorTotal()));
			}

			session.save(pedido);

			/* COMITA DADOS NO BANCO */
			session.getTransaction().commit();

			XStream xstream = new XStream();
			xstream.autodetectAnnotations(true);
			xstream.alias("cliente", Cliente.class);
			xstream.alias("vendedor", Vendedor.class);
			xstream.alias("pedido", Pedido.class);
			xstream.alias("produto", Produto.class);
			xstream.alias("item", Item.class);
			xstream.addImplicitCollection(Pedido.class, "listaitens", "item", Item.class);

			System.out.print(xstream.toXML(cliente));
			System.out.print(xstream.toXML(vendedor));
			System.out.print(xstream.toXML(lapis));
			System.out.print(xstream.toXML(caneta));
			System.out.print(xstream.toXML(borracha));
			System.out.print(xstream.toXML(estojo));
			System.out.print(xstream.toXML(caderno));
			System.out.print(xstream.toXML(pedido));

			System.out.println("-----------------------------------------");
			List<Pedido> listaPedidos = session.createCriteria(Pedido.class).list();

			xstream.ignoreUnknownElements();

			for (Pedido pedidos : listaPedidos) {
				System.out.print(xstream.toXML(pedidos));
			}
			System.out.println();
			
			JsonUtils json = new JsonUtils();
			System.out.print("{\"cliente\": " + json.toJson(cliente) + "}");
			System.out.println();
			System.out.print("{\"vendedor\": " + json.toJson(vendedor) + "}");
			System.out.println();
			System.out.print("{\"produto\":" + json.toJson(caneta) + "}");
			System.out.println();
			json = new JsonUtils("pedido");
			System.out.print("{\"pedido\": " + json.toJson(pedido) + "}");
			
			
			session.close();

			System.exit(0);

		} catch (Exception e) {
			session.close();
			System.exit(0);
		}
	}

	private static Cliente criaCliente(Session session) {
		Cliente cliente = new Cliente();
		cliente.setNome("CLIENTE NOVO");
		cliente.setDataNascimento(Date.from(LocalDate.of(1987, 10, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));

		session.save(cliente);
		return cliente;
	}

	private static Vendedor criaVendedor(Session session) {
		Vendedor vendedor = new Vendedor();
		vendedor.setNome("ANTONIO CARLOS");
		vendedor.setDocumento("38385077545");
		vendedor.setDataNascimento(Date.from(LocalDate.of(1980, 01, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));

		session.save(vendedor);
		return vendedor;
	}
}
