import java.io.File;
import java.io.IOException;

public class Archivo{

    public static void main(String[] args) {
    	
    	if (args.length == 3) {
    		String directorioCliente = args[0];
    		//Directorio del cliente, en al cual estan los archivos PDF en distintas carpetas
    		File diradquirirArchivos = new File("C:\\Users\\mcasteletti\\Desktop\\76089459-1\\sent");
    		//Directorio en el cual se dejarán los documentos PDF
    		File dirOrigen = new File ("C:\\Users\\mcasteletti\\Desktop\\76089459-1\\Origen\\");
    		//Directorio de destino
    		File dirDestino = new File ("C:\\Users\\mcasteletti\\Desktop\\76089459-1\\Destino\\");
    		
    		adquirirArchivos(diradquirirArchivos, dirOrigen);
    		moverAFecha(dirOrigen,dirDestino);
    		cambiarNombre(dirDestino);
    	} else {
    		System.out.println("Ingrese argumentos");
    	}
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
                	String rutEmisor = nombre.substring(21,29);
            		int folio = Integer.parseInt(nombre.substring(6,20));
            		int tipoDocu = Integer.parseInt(nombre.substring(1,4));
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