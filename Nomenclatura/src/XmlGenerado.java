import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlGenerado {
	
	private File dirOrigen, dirDestino;


	
	public XmlGenerado() {
		
	}
	public XmlGenerado(File dirOrigen, File dirDestino) {
		this.dirOrigen=dirOrigen;
		this.dirDestino=dirDestino;

	}
	
	
	public String buscarDatos(File archivo) {
		String nombre ="";
		try {
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  Document doc = dBuilder.parse(archivo);
			  
			  NodeList nList = doc.getElementsByTagName("IdDoc");
			  NodeList nRut = doc.getElementsByTagName("Emisor");
			  
			  
			  for(int temp = 0; temp < nList.getLength(); temp++) {
				  Node nNode = nList.item(temp);

				  if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				    Element eElement = (Element) nNode;

				    
				    
				    for(int temprut = 0; temprut < nRut.getLength(); temprut++) {
				    	Node nNodeRut = nRut.item(temprut);
				    	if(nNodeRut.getNodeType() == Node.ELEMENT_NODE) {
						    Element eElementRut = (Element) nNodeRut;
						    
						  //Asignar valor del TAG
						    int tipo = Integer.parseInt(eElement.getElementsByTagName("TipoDTE").item(0).getTextContent());
						    int folio = Integer.parseInt(eElement.getElementsByTagName("Folio").item(0).getTextContent());
						    String date = eElement.getElementsByTagName("FchEmis").item(0).getTextContent();
						    String rut = eElementRut.getElementsByTagName("RUTEmisor").item(0).getTextContent();
						    
						    System.out.println("Tipo: "+ tipo);
						    System.out.println("Folio: "+ folio);
						    System.out.println("Fecha: "+ date);
						    System.out.println("Rut: "+rut);
						    nombre = date+"_"+rut+"_"+folio+"_"+tipo+".xml";
						    System.out.println(nombre);
						    return nombre;
				    	}
				    }

				   
				  }
				}
			  
			} catch(Exception e) {
				
			  e.printStackTrace();
			  return nombre;
			}
		return nombre;
	}
	
	public String renombrar(File archivo) {
		String nombre ="";
		try {
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  Document doc = dBuilder.parse(archivo);
			  
			  NodeList nList = doc.getElementsByTagName("IdDoc");
NodeList nRut = doc.getElementsByTagName("Emisor");
			  
			  
			  for(int temp = 0; temp < nList.getLength(); temp++) {
				  Node nNode = nList.item(temp);

				  if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				    Element eElement = (Element) nNode;

				    
				    
				    for(int temprut = 0; temprut < nRut.getLength(); temprut++) {
				    	Node nNodeRut = nRut.item(temprut);
				    	if(nNodeRut.getNodeType() == Node.ELEMENT_NODE) {
						    Element eElementRut = (Element) nNodeRut;
						    
						  //Asignar valor del TAG
						    int tipo = Integer.parseInt(eElement.getElementsByTagName("TipoDTE").item(0).getTextContent());
						    int folio = Integer.parseInt(eElement.getElementsByTagName("Folio").item(0).getTextContent());
						    String date = eElement.getElementsByTagName("FchEmis").item(0).getTextContent();
						    String rut = eElementRut.getElementsByTagName("RUTEmisor").item(0).getTextContent();
						    
						    System.out.println("Tipo: "+ tipo);
						    System.out.println("Folio: "+ folio);
						    System.out.println("Fecha: "+ date);
						    System.out.println("Rut: "+rut);
						    nombre = rut.substring(0,8)+"_"+folio+"_"+tipo+".xml";
						    System.out.println(nombre);
						    return nombre;
				    	}
				    }

				   
				  }
				}
			  
			} catch(Exception e) {
				
			  e.printStackTrace();
			  return nombre;
			}
		return nombre;
	}
	
	
	
	public  void moverAFecha(File dirOrigen, File dirDestino) {
    	File[] files = dirOrigen.listFiles();
    	File directorioDestino = dirDestino;

    	for (File file : files) {
    		XmlGenerado xg = new XmlGenerado();
    		xg.buscarDatos(file);
    		
    		
    		
    		String nombre = xg.buscarDatos(file);
    		String[] parts = nombre.split("_");
    		String date =parts[0];
    		String[] dateArray = date.split("-");
    		System.out.println("Fecha del documento" + date);
    		String anho = dateArray[0];
    		System.out.println("año: "+anho);
    		String mes = dateArray[1];
    		System.out.println("mes: "+mes);
    		String dia = dateArray[2];
    		System.out.println("dia: "+dia);
        	File fileDestinoAnho = new File(directorioDestino,anho);
        	File fileDestinoMes = new File(fileDestinoAnho,mes);
        	File fileDestinoDia = new File(fileDestinoMes,dia);

       		if(!fileDestinoAnho.exists()) fileDestinoAnho.mkdirs();
        	if(!fileDestinoMes.exists()) fileDestinoMes.mkdirs();
        	if(!fileDestinoDia.exists()) fileDestinoDia.mkdirs();
        	
        	String nombreFinal = xg.renombrar(file);
        	
    		File archivoAMover = new File(fileDestinoDia+"\\"+nombreFinal);
    		boolean estadoMovimiento =  file.renameTo(archivoAMover);
           	if (estadoMovimiento) System.out.println("Documento movido correctamente: "+ file.getName());
           	else System.out.println("Error al mover: " + file.getName());
           	}
    	System.out.println("FIN");
    }
	
	
	
	
	
	
	
	public static void adquirirArchivos(File dir, File orig) {
        try {
            File[] files = dir.listFiles();
            //Se recorre el arreglo de Files
            for (File file : files) {
            	//Se consulta si file es un directorio, si lo es sigue un directorio más adelante
                if (file.isDirectory()) {
                    System.out.println("directorio:" + file.getCanonicalPath());
                    adquirirArchivos(file,orig);
                //Si no es un directorio, se comienzan a mover los documentos
                } else {
                	//Se crea carpeta contenedora
                	if(!orig.exists())	orig.mkdirs();
                	//Se mueven los ficheros
               		File archivoAMover = new File(orig +"\\"+ file.getName());
               		System.out.println(archivoAMover);
                  	boolean estadoMovimiento =  file.renameTo(archivoAMover);
                  		if (estadoMovimiento) System.out.println("Documento movido correctamente: "+ file.getName());
                    	else System.out.println("Error al mover" + file.getName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	
	
	
	
	

}
