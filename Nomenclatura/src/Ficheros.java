import java.io.File;
import java.io.IOException;

public class Ficheros{

    public static void main(String[] args) {
    	
    	File dirDestino = new File ("C:\\Users\\mcasteletti\\Desktop\\76089459-1\\asd");
    	cambiarNombre(dirDestino);
    	
//    	if (args.length == 1) {
//    		String directorioCliente = args[0];
//
//    		//Directorio del cliente, en al cual estan los archivos PDF en distintas carpetas
//    		File diradquirirArchivos = new File(directorioCliente);
//    		//Directorio en el cual se dejarán los documentos PDF
//    		File dirOrigen = new File (diradquirirArchivos+"\\Origen\\");
//    		//Directorio de destino
//    		File dirDestino = new File (diradquirirArchivos+"\\\\Destino\\\\");
//    		
//    		adquirirArchivos(diradquirirArchivos, dirOrigen);
//    		moverAFecha(dirOrigen,dirDestino);
//    		cambiarNombre(dirDestino);
//    	} else {
//    		System.out.println("Ingrese ruta de los directorios");
//    	}
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
    		//System.out.println(file.getName());
    		String nombre = file.getName();
    		String anho = nombre.substring(41,45);
    		String mes = nombre.substring(45,47);
    		String dia = nombre.substring(47,49);
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
    }
    
//	String nombre = "C:\\Users\\mcasteletti\\Desktop\\76089459-1\\sent\\Destino\\2013\\07\\22\\76089459-.2.-34.pdf";
//	String extension = nombre.substring(nombre.lastIndexOf("."), nombre.length());
//	String nombreSinExt = nombre.substring(0,nombre.lastIndexOf("."));

    
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
                	int  rutEmisor = Integer.parseInt(nombre.substring(nombre.lastIndexOf("S")+1,nombre.lastIndexOf("R")-1));
            		int folio = Integer.parseInt(nombre.substring(nombre.lastIndexOf("N")+1,nombre.lastIndexOf("S")));
            		int tipoDocu = Integer.parseInt(nombre.substring(nombre.lastIndexOf("T")+1,nombre.lastIndexOf("N")));
            		File newNombre = new File (rutEmisor+"-"+folio+"-"+tipoDocu);
            		
            		System.out.println("Archivo a modificar: "+ file.getAbsolutePath());
            		System.out.println("Nombre: "+nombre);
            		System.out.println("RutEmisor: "+rutEmisor);
            		System.out.println("Folio: "+folio);
            		System.out.println("TipoDocumento: "+tipoDocu);
            		
            		File archivoAMover = new File(dirDestino +"\\"+ newNombre+".pdf");
                  	boolean estadoMovimiento =  file.renameTo(archivoAMover);
                 	if (estadoMovimiento) System.out.println("Documento modificado correctamente: "+ newNombre+".pdf");
                    else System.out.println("Error al modificar nombre: " + file.getName());
                 		
                   	System.out.println("Ejecución Realizada");
                   	System.out.println("==================");
            		
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}