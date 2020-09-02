import java.io.File;
import java.io.IOException;

public class Archivo{

    public static void main(String[] args) {

    		//Directorio del cliente, en al cual estan los archivos PDF en distintas carpetas
    		File diradquirirArchivos = new File("C:\\nomenclatura\\Origen");
    		//Directorio en el cual se dejarán los documentos PDF
    		File dirOrigen = new File ("C:\\nomenclatura\\Transicion");
    		//Directorio de destino
    		File dirDestino = new File ("C:\\nomenclatura\\Destino");
    		
    		adquirirArchivos(diradquirirArchivos, dirOrigen);
    		moverAFecha(dirOrigen,dirDestino);
    		cambiarNombre(dirDestino);

    	
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
    
    
    public static void moverAFecha(File dirOrigen, File dirDestino) {
    	File[] files = dirOrigen.listFiles();
    	File directorioDestino = dirDestino;

    	for (File file : files) {
    		
    		String nombre = file.getName();
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
        	
    		File archivoAMover = new File(fileDestinoDia+"\\"+nombre);
    		boolean estadoMovimiento =  file.renameTo(archivoAMover);
           	if (estadoMovimiento) System.out.println("Documento movido correctamente: "+ file.getName());
           	else System.out.println("Error al mover: " + file.getName());
           	}
    	System.out.println("FIN");
    }
    
    
    public static void cambiarNombre(File dirDestino) {
        try {
            File[] files = dirDestino.listFiles();
            //Se recorre el arreglo de Files
            for (File file : files) {
            	//Se consulta si file es un directorio, si lo es sigue un directorio más adelante
                if (file.isDirectory()) {
                    System.out.println("directorio:" + file.getCanonicalPath());
                    cambiarNombre(file);
                //Si no es un directorio, se comienzan a mover los documentos
                } else {
                	String nombre = file.getName();
                	String[] parts = nombre.split("_");
                	
                	String rutEmisorConExentecion = parts[4];


                	String rutEmisor=rutEmisorConExentecion.substring(0,8);

                	System.out.println("****"+rutEmisor);
                	
            		int folio = Integer.parseInt(parts[1]);
            		int tipoDocu = Integer.parseInt(parts[2]);
            		File newNombre = new File (rutEmisor+"-"+folio+"-"+tipoDocu);
            		
            		System.out.println("Archivo a modificar: "+ file.getAbsolutePath());
            		System.out.println("Nombre: "+nombre);
            		System.out.println("RutEmisor: "+rutEmisor);
            		System.out.println("Folio: "+folio);
            		System.out.println("TipoDocumento: "+tipoDocu);
            		
            		System.out.println(rutEmisorConExentecion.substring(9,12));
            		
            		if (rutEmisorConExentecion.substring(9,12).equals("xml")) {
            			File archivoAMover = new File(dirDestino +"\\"+ newNombre+".xml");
                      	boolean estadoMovimiento =  file.renameTo(archivoAMover);
                     	if (estadoMovimiento) System.out.println("Documento modificado correctamente: "+ newNombre+".xml");
                        else System.out.println("Error al modificar nombre: " + file.getName());
                     		
                       	System.out.println("Ejecución Realizada");
                       	System.out.println("==================");
            		}
            		if(rutEmisorConExentecion.substring(9,12).equals("pdf")) {
            			File archivoAMover = new File(dirDestino +"\\"+ newNombre+".pdf");
                      	boolean estadoMovimiento =  file.renameTo(archivoAMover);
                     	if (estadoMovimiento) System.out.println("Documento modificado correctamente: "+ newNombre+".pdf");
                        else System.out.println("Error al modificar nombre: " + file.getName());
                     		
                       	System.out.println("Ejecución Realizada");
                       	System.out.println("==================");
            		}

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}