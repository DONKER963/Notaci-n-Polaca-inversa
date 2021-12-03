package notacionpolacastring;

import java.util.Scanner;

class cola {
    String A[];
    int tope,fin,Tamanio,c;
    String aux;

    public cola(int b){
	tope = fin=0;
	A = new String[b];
	Tamanio = b;
}//constructor
    public void Insertar(String c){
	A[fin]=c;
	fin++;
}
    public void Extraer(){
        aux =A[tope];
        tope++;
}//Extrae
    public int Vacia(){
	if(tope == fin)
	{
		return 1;
	}
	else{return 0;
	}
}//vacia
    public int Llena(){
	if(fin == Tamanio)
	{
		return 1;
	}
	else{return 0;
	}
}//llena
    public void mostrar() {
        for(int i=tope; i< Tamanio; i++){
            if(A[i]!=null){
                System.out.print(A[i]+" ");
            }
        }
        System.out.print("\n");
        //System.out.println("|\nFrente "+tope+" Atras "+fin+" Tamaño  "+Tamanio);
    }//muestra
}
class Pila {
    String aux;
    public String A[];
    public int tope;
    public int tamaño;
    
    public Pila(int t){
          tamaño=t; 
          tope=0;
          A= new String[t]; 
    }//constructor
    public void push(String dato){
        A[tope]=dato;
        tope++;
    }//Insertar
    public void pop(){
        tope--;
        aux = A[tope];
    }//eliminar
    public int vacia(){
        if(tope==0) return 1;
        else return 0;
    }//vacia?
    public int llena(){
        if(tope==tamaño) return 1;
        else return 0;
    }//llena?
    public void mostrar(){
        for(int i=0; i< tope; i++){
        System.out.print(A[i]);}
        System.out.print("\n");
    }//muestra
}

class NPI{
    Scanner leer = new Scanner(System.in);
    char au;
    String auxiliar,dos,formula;
    float iz,de;
    cola a,c;
    Pila b,d;
    
    public NPI(){
        a = new cola(20);
        c = new cola(20);
        b = new Pila(20);
        d = new Pila(20);
    }
    
    public void notacioPosfija() {
        while (a.Vacia() == 0) {//mientras la cola a no este vacia hacemos
            a.Extraer();//sacamos un valor
            //System.out.println("se extrajo "+a.aux);//mostramos lo que se extrajo
            //c.mostrar();
            //b.mostrar();
            auxiliar = a.aux;

            if ((auxiliar.equals("+") == true) || (auxiliar.equals("-") == true)) {
                //Si es un op de rango1
                for (int i = 0; i < b.tope; i++) {//buscamos op de mayor rango
                    //System.out.print(b.A[i]);//mostramos antes de hacer un cambio
                    if ((b.A[i].equals("/") == true) || (b.A[i].equals("*") == true)) {
                        //si hay op mayor rango hacemos
                        c.Insertar(b.A[i]);
                        b.A[i] = "0";
                    }
                    //System.out.print(b.A[i]);//mostramos despues de hacer cambio
                }
                for (int i = 0; i < b.tope; i++) {//buscamos de mismo rango
                    //System.out.print(b.A[i]);//mostramos antes de hacer un cambio
                    if ((b.A[i].equals("+") == true) || (b.A[i].equals("-") == true)) {
                        //si hay ope de mismo rango hacemos
                        c.Insertar(b.A[i]);
                        b.A[i] = "0";
                    }
                    //System.out.print(b.A[i]);//mostramos despues de hacer cambio
                }
                //System.out.println("");
                b.push(auxiliar);
            } else {//si nos es un op de rango 1
                if ((auxiliar.equals("/") == true) || auxiliar.equals("*") == true) {
                    //si es op de rango 2
                    for (int i = 0; i < b.tope; i++) {//buscamos op de mismo rango
                        //System.out.print(b.A[i]);//mostramos antes de hacer cambio
                        if ((b.A[i].equals("*") == true) || (b.A[i].equals("/") == true)) {
                            //si hay op de mismo rango hacemos
                            c.Insertar(b.A[i]);
                            b.A[i] = "0";
                        }
                        //System.out.print(b.A[i]);//mostramos despues de hacer cambio
                    }
                    //System.out.print("\n");
                    b.push(auxiliar);
                } else {//si no es operando de rango 2
                    if ((auxiliar.equals("=") == true)) {//debe ser un igual
                        b.push(auxiliar);
                    } else {//sino, por ende es numero o variable
                        c.Insertar(auxiliar);
                    }
                }
            }
        }
        
        for (int i = 0; i < b.tope; i++) {
            if ((b.A[i].equals("*") == true) || (b.A[i].equals("/") == true)) {
                c.Insertar(b.A[i]);
                b.A[i] = "0";
            }
        }
        for (int i = 0; i < b.tope; i++) {
            if ((b.A[i].equals("+") == true) || (b.A[i].equals("-") == true)) {
                c.Insertar(b.A[i]);
                b.A[i] = "0";
            }
        }
        for (int i = 0; i < b.tope; i++) {
            if (b.A[i].equals("=") == true) {
                c.Insertar(b.A[i]);
                b.A[i] = "0";
            }
        }
        //b.mostrar();
        System.out.println("Cola de salida");
        c.mostrar();
    }
    
    public void evaluacion(){
        System.out.println("Evaluación");
        while(c.Vacia()==0){
            c.Extraer();
            //System.out.println(c.aux);
            //d.mostrar();
            if("-".equals(c.aux)==true){
                //System.out.println("resta");
                d.pop();
                de = Float.parseFloat(d.aux);
                d.pop();
                iz = Float.parseFloat(d.aux);
                System.out.println(iz +" + "+de+" = "+(iz-de));
                d.push(String.valueOf(iz-de));
                //d.mostrar();
            }
            if("+".equals(c.aux)==true){
                //System.out.println("suma");
                d.pop();
                de = Float.parseFloat(d.aux);
                d.pop();
                iz = Float.parseFloat(d.aux);
                System.out.println(iz +" + "+de+" = "+(iz+de));
                d.push(String.valueOf(iz+de));
                //d.mostrar();
            }
            
            if("*".equals(c.aux)==true){
                //System.out.println("multi");
                d.pop();
                de = Float.parseFloat(d.aux);
                d.pop();
                iz = Float.parseFloat(d.aux);
                System.out.println(iz +" + "+de+" = "+(iz*de));
                d.push(String.valueOf(iz*de));
                //d.mostrar();
            }
            if("/".equals(c.aux)==true){
                //System.out.println("divi");
                d.pop();
                de = Float.parseFloat(d.aux);
                d.pop();
                iz = Float.parseFloat(d.aux);
                System.out.println(iz +" / "+de+" = "+(iz/de));
                d.push(String.valueOf(iz/de));
                //d.mostrar();
            }
            if(("=".equals(c.aux)==false)&&("/".equals(c.aux)==false)&&("*".equals(c.aux)==false)&&("+".equals(c.aux)==false)&&("-".equals(c.aux)==false)){
                //si no es operando hacemos
                //System.out.println("numero");
                d.push(c.aux);
                //d.mostrar();
            }
            if("=".equals(c.aux)==true){
                //System.out.println("igual");
                //d.mostrar();
                d.pop();
                de = Float.parseFloat(d.aux);
                d.pop();
                auxiliar = d.aux;
                System.out.println(auxiliar+" = "+de);
            }
        }
    }
    
}

public class NotacionPolacaString {

    public static void main(String[] args) {
        // TODO code application logic here
        NPI pol = new NPI();
        
        pol.a.Insertar("1");
        pol.a.Insertar("+");
        pol.a.Insertar("2");
        pol.a.mostrar();
        pol.notacioPosfija();
        //evaluacion
        pol.evaluacion();
    }
}
