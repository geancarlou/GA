import java.math.BigDecimal;
import java.util.ArrayList;
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
			Produto caderno = new Produto();
			caderno.setNome("Caderno");
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

			pedido.setListaitens(listaItens);
			pedido.setValorTotalPedido(BigDecimal.ZERO);

			for (Item item : listaItens) {
				pedido.setValorTotalPedido(pedido.getValorTotalPedido().add(item.getValorTotal()));
			}

			session.save(pedido);

			/* COMITA DADOS NO BANCO */
			session.getTransaction().commit();

			// XML
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
			System.out.print(xstream.toXML(caderno));
			System.out.print(xstream.toXML(pedido));

			System.out.println("-----------------------------------------");
			List<Pedido> listaPedidos = session.createCriteria(Pedido.class).list();

			xstream.ignoreUnknownElements();

			for (Pedido pedidos : listaPedidos) {
				System.out.print(xstream.toXML(pedidos));
			}
			System.out.println();
			
			
			// JSON
			JsonUtils json = new JsonUtils();
			System.out.print("{\"cliente\": " + json.toJson(cliente) + "}");
			System.out.println();
			System.out.print("{\"vendedor\": " + json.toJson(vendedor) + "}");
			System.out.println();
			System.out.print("{\"produto\":" + json.toJson(caderno) + "}");
			System.out.println();
			json = new JsonUtils("pedido");
			System.out.print("{\"pedido\": " + json.toJson(pedido) + "}");
			
			//QUERIES
			Queries(session);
			
			session.close();

			System.exit(0);

		} catch (Exception e) {
			session.close();
			System.exit(0);
		}
	}

	private static Cliente criaCliente(Session session) {
		Cliente cliente = new Cliente();
		cliente.setNome("Luís Pozenato");
		session.save(cliente);
		return cliente;
	}

	private static Vendedor criaVendedor(Session session) {
		Vendedor vendedor = new Vendedor();
		vendedor.setNome("Geancarlo Urnau");
		vendedor.setDocumento("38385077545");
		session.save(vendedor);
		return vendedor;
	}
	
	private static void Queries(Session session){
		//Selects Vendedores
		List<Vendedor> vendedores = session.createQuery("SELECT v FROM Vendedor v").getResultList();
		System.out.print("##### Vendedores #####\n");
		for(Vendedor v : vendedores){
			System.out.print(v.toString() + "\n");
		}
		
		//Selects Clientes
		List<Cliente> clientes= session.createQuery("SELECT c FROM Cliente c").getResultList();
		System.out.print("##### Clientes #####\n");
		for(Cliente c : clientes){
			System.out.print(c.toString() + "\n");
		}
		
		//Selects Produtos
		List<Produto> produtos= session.createQuery("SELECT p FROM Produto p").getResultList();
		System.out.print("##### Produtos #####\n");
		for(Produto p : produtos){
			System.out.print(p.toString() + "\n");
		}
		
		//Selects Pedidos
		List<Pedido> pedidos= session.createQuery("SELECT p FROM Pedido p").getResultList();
		System.out.print("##### Pedidos #####\n");
		for(Pedido p : pedidos){
			System.out.print(p.toString() + "\n");
		}
	}
}
