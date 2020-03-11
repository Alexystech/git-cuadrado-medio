import javax.swing.JOptionPane;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class cuadradoMedio
{
    private static final InputStreamReader isr = new InputStreamReader(System.in);
    private static final BufferedReader br = new BufferedReader(isr);
    private static List <Float> almacen_r = new ArrayList<Float>();
    private static List <Integer> almacen_y = new ArrayList<Integer>();
    private static List <Integer> almacen_Valores_exponent = new ArrayList<Integer>();
    private static List <Integer> almacen_semillas = new ArrayList<Integer>();
    public static void main(String args[]) throws IOException
    {
        boolean return_main = true;

        while (return_main)
        {
            try
            {
                int semilla = 0;
                int cantidad_r = 0;
    
                do
                {
                    System.out.println("Ingresa el numero de 4 dijitos para la semilla x0:");
                    semilla = Integer.parseInt(br.readLine());
                    System.out.println("Ingresa un numero de ri que deseas:");
                    cantidad_r = Integer.parseInt(br.readLine());
    
                    if (!is_greater_to_three(semilla))
                    {
                        JOptionPane.showMessageDialog(null,"error de cantidad");
                    }
    
                }while(!is_greater_to_three(semilla));
    
                almacen_y.add(semilla);
                obtener_r(semilla, cantidad_r);
    
                imprimir_datos(cantidad_r);
                return_main = false;
            }
            catch (Exception e) 
            {
                JOptionPane.showMessageDialog(null,"error de tipo");
            }
        }
    }

    private static boolean is_greater_to_three(int semilla)
    {
        String semilla_text = Integer.toString(semilla);
        return (semilla_text.length() > 3)? true : false;
    }

    private static void imprimir_datos(int cantidad_r)
    {
        for (int x = 0; x < cantidad_r; x++)
        {
            System.out.println
            (
                "y"+x+" = ("+
                almacen_y.get(x)+")^2 = "+
                almacen_Valores_exponent.get(x)+" x"+(x+1)+" = "+
                almacen_semillas.get(x)+" r"+(x+1)+" = "+
                almacen_r.get(x)
            );
        }
    }

    private static float obtener_r(int semilla, int cantidad_r)
    {
        if (cantidad_r == 0)
        {
            return 0;
        }
        else
        {
            almacen_r.add((float) (new_zen(zen_exponent(semilla)))/10000);
            almacen_y.add(new_zen(zen_exponent(semilla)));
            almacen_Valores_exponent.add((int)zen_exponent(semilla));
            almacen_semillas.add(new_zen(zen_exponent(semilla)));

            return obtener_r(new_zen(zen_exponent(semilla)), cantidad_r-1);
        }
    }

    private static double zen_exponent(int semilla)
    {
        return Math.pow(semilla,2);
    }

    /**
     * debo corregir la convercion de double a texto y viceversa porque lee mas
     * caracteres de los que son ejemplo
     * 102030.0
     * return 8 dijitos
     * cuabdo tienen que ser 6 dijitos
     */
    private static int new_zen(double value_exponent)
    {
        int valor = (int)value_exponent;
        String text_value_exponent = Integer.toString(valor);
        int new_zen_numerico = 0;

        if ( (text_value_exponent.length()%2) != 0) 
        {
            text_value_exponent = "0"+text_value_exponent;
            int valor_limite_inicial_final = (text_value_exponent.length() - 4) / 2;
            String new_zen_texto = text_value_exponent.substring(valor_limite_inicial_final, valor_limite_inicial_final+4);
            new_zen_numerico = Integer.parseInt(new_zen_texto);
        }
        else
        {
            int valor_limite_inicial_final = (text_value_exponent.length() - 4) / 2;
            String new_zen_texto = text_value_exponent.substring(valor_limite_inicial_final, valor_limite_inicial_final+4);
            new_zen_numerico = Integer.parseInt(new_zen_texto);
        }
        return new_zen_numerico;
    }
}