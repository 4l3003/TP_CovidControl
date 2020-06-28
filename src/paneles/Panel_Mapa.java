package paneles;



import javax.swing.JPanel;

import com.teamdev.jxmaps.Circle;
import com.teamdev.jxmaps.CircleOptions;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapEvent;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.MapTypeId;
import com.teamdev.jxmaps.MapTypeStyle;
import com.teamdev.jxmaps.MapTypeStyleElementType;
import com.teamdev.jxmaps.MapTypeStyleFeatureType;
import com.teamdev.jxmaps.MapTypeStyler;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.StyledMapType;
import com.teamdev.jxmaps.swing.MapView;

import clases.ListadoPersonas;
import clases.Persona;
import ventanas.VentanaDialogPersona;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Map.Entry;
/**
 * Esta clase hereda de panel y realiza todo lo relacionado con el mapa. la misma se instancia tanto para agregar un usuario al mapa como para
 * ver el mismo. Nos muestra el mapa y la barra lateral con las opciones.
 * @author Alejo Bocanegra y Martin Molina.
 *
 */
public class Panel_Mapa extends Panel  {
	
	private static final long serialVersionUID = 7889605382224247835L;
	private Map map;
	private JPanel panel;
	private MapView view = new MapView();
	private JLabel boton, boton2, boton3, label1, label2, label3;
	private Marker mark;
	private Image img1 = new ImageIcon(Panel_Login.class.getResource("/img/addiconNegro.png")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
	private Image img2 = new ImageIcon(Panel_Login.class.getResource("/img/addiconAzul.png")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
	private Image img3 = new ImageIcon(Panel_Login.class.getResource("/img/iconAddrojo.png")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
	private ListadoPersonas listadoPerso;
	
	/**
	 * Contructor que iguala el listado de personas, llama a dos metodos principales, el encargado de gestionar el mapa y el encargado
	 * de la barra lateral. 
	 * @param listadoPerso
	 * @param estado
	 */
	public Panel_Mapa(ListadoPersonas listadoPerso, boolean estado)
		{
			setLayout(null);
			this.listadoPerso = listadoPerso;
			mapa(estado);	
			panelLateral();
			super.botonCerrar(880);
			
		}
		/**
		 * crea Un panel y lo agrega a la ventana.
		 */
		public void panelLateral()
		{
			Panel lateral = new Panel();
		
			lateral.setBounds(830, 0, 70, 595);
			lateral.setBackground(Color.BLACK);
			lateral.setVisible(true);
			lateral.add(super.botonCerrar(880));
			add(lateral);
		}
	
		/**
		 * Realiza una comparacion interna segun el boolean que recibe, si el mismo es "true", podremos agregar puntos al mapa y si es "false"
		 * solo lo visualizaremos.
		 * @param estado
		 */
		public void mapa(boolean estado)
		{
			view.setOnMapReadyHandler(new MapReadyHandler(){
				
				@Override
				public void onMapReady(MapStatus status) {
					if(status == MapStatus.MAP_STATUS_OK)
					{
						
						map = view.getMap();
						MapOptions mapOptions = new MapOptions();
						MapTypeControlOptions controlOptions = new MapTypeControlOptions();
						mapOptions.setMapTypeControlOptions(controlOptions);
						mapOptions.setZoomControl(false);
						mapOptions.setStreetViewControl(false);
						sacarEtiquetasMapa();
						map.setOptions(mapOptions);
						map.setCenter(new LatLng(-38.00563, -57.53381)); 
						map.setZoom(11.0);
						if(estado)
						{
							mark = new Marker(map);
							mark.setPosition(map.getCenter());
							mark.setDraggable(true);
							
							final InfoWindow window = new InfoWindow(map);
		                    window.open(map, mark);
							window.setContent("Mueva el Marcador hacia donde desee setear el punto y luego presione en el boton + correspondiente de la derecha");
						}
						else
						{
							
							mostrarPuntosMapa();
						}
						
					}	
				}
			});
			view.setBounds(0, 0,830, 595);
			add(view);
		}
		/**
		 * Contiene las acciones de los botones de la barra lateral para agregar un punto al mapa.
		 */
		public void agregar() {
			
			setLayout(null);
			panel = new JPanel();
			
			panel.setBackground(new Color(0, 139, 139));
			panel.setBounds(815, 0, 100, 600);
			add(panel);
			
			boton = new JLabel("");
			
			boton.setIcon(new ImageIcon(img1));
			
			boton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					crearPuntoMapa("#000000", mark.getPosition(), 1);	
				}	
			});
			boton.setBounds(29, 60, 100, 100);
			boton.setVisible(true);
			panel.setLayout(null);
			panel.add(boton);
			
			
			boton2 = new JLabel("");
			boton2.setIcon(new ImageIcon(img2));
			
			boton2.setBounds(29, 120, 100, 100);
			boton2.setVisible(true);
			panel.add(boton2);
			boton2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					crearPuntoMapa("#0000FF",mark.getPosition(), 2);
					
				}
			});
			
			boton3 = new JLabel("");
			boton3.setIcon(new ImageIcon(img3));
			
			boton3.setBounds(29, 180, 100, 100);
			boton3.setVisible(true);
			panel.add(boton3);
			boton3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					crearPuntoMapa("#FF0000", mark.getPosition(), 3);
					
				}
			});
			label1 = new JLabel("En Testeo");
			label1.setBounds(26, 80, 100, 100);
			label1.setVisible(true);
			panel.add(label1);
			label2 = new JLabel("Positivo");
			label2.setBounds(26, 140, 100, 100);
			label2.setVisible(true);
			panel.add(label2);
			label3 = new JLabel("Negativo");
			label3.setBounds(26, 200, 100, 100);
			label3.setVisible(true);
			panel.add(label3);
		}
		/**
		 * Cambia la configuracion del mapa sacandole al mismo las etiquetas de los negocios o locales que figuran en google maps.
		 */
		public void sacarEtiquetasMapa()
		{
			MapTypeStyler styler = new MapTypeStyler();
			styler.setVisibility("off");

			MapTypeStyle style = new MapTypeStyle();
			style.setElementType(MapTypeStyleElementType.ALL);
			style.setFeatureType(MapTypeStyleFeatureType.POI);
			style.setStylers(new MapTypeStyler[]{styler});

			StyledMapType styledMap = new StyledMapType(map, new MapTypeStyle[]{style});
			map.mapTypes().set("newStyle", styledMap);
			map.setMapTypeId(new MapTypeId("newStyle"));
		}
		
		/**
		 * Crea un punto en el mapa del color en rgb que le pasamos por parametro,y en la ubicacion que recibe
		 * por parametro.
		 * @param color
		 * @param coord
		 * @param Nbtn
		 */
		public void crearPuntoMapa(String color, LatLng coord, int Nbtn)
		{
			
			Circle circle = new Circle(map);
			circle.setCenter(mark.getPosition());
			circle.setRadius(15);
			CircleOptions circleOptions = new CircleOptions();
			circleOptions.setFillColor(color);
			circleOptions.setStrokeColor(color);
			circleOptions.setFillOpacity(1);
			circle.setOptions(circleOptions);
			
			VentanaDialogPersona regisPersona = new VentanaDialogPersona(coord, Nbtn,listadoPerso);
			regisPersona.setLocationRelativeTo(null);
			regisPersona.setVisible(true);
			 
			
			
			listadoPerso.agregarPersona(regisPersona.getPersona());	
		}
		/**
		 * Muestra los puntos en el mapa del color correspondiente
		 */
		public void mostrarPuntosMapa()
		{
			String color;
			Iterator<Entry<String, Persona>> it = listadoPerso.getListadopersona().entrySet().iterator();
			while (it.hasNext())
			{
				
				Entry<String, Persona> e = (Entry<String, Persona>)it.next();
				if(e.getValue().getEstado().equals("En testeo")) 
				{  
					
					color = "#000000";
				} else if(e.getValue().getEstado().equals("Positivo")) 
				{ 
					color = "#0000FF";
				} 
				else 
				{ 
					color = "#FF0000";
				}
				
				 
				 Circle circle = new Circle(map);
				
				 circle.setCenter(e.getValue().getCoord());
				 circle.setRadius(15);
				 CircleOptions circleOptions = new CircleOptions();
				 circleOptions.setClickable(true);
				 circleOptions.setFillColor(color); 
				 circleOptions.setStrokeColor(color); 
				 circleOptions.setFillOpacity(1);
				 final InfoWindow window = new InfoWindow(map);
				 circle.addEventListener("mouseover", new MapEvent() {
					   @Override
					   public void onEvent() {
						  
						   window.setPosition(circle.getCenter());
							window.setContent(e.getValue().toString());
						    window.open(map,circle);
					     
					   }
					});
				 circle.setOptions(circleOptions);
			}
	
		}
		
		
}