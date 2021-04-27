package pojo;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="prestamo")
@XmlAccessorType(XmlAccessType.FIELD)
public class Prestamo
{
    @XmlElement(name = "libro")
    private String libro;

    @XmlElement(name = "cliente")
    private String cliente;

    @XmlElement(name = "fechadevolucion")
    private String fechadevolucion;

    @XmlElement(name = "fechaprestamo")
    private String fechaprestamo;

    public String getLibro ()
    {
        return libro;
    }

    public void setLibro (String libro)
    {
        this.libro = libro;
    }

    public String getCliente ()
    {
        return cliente;
    }

    public void setCliente (String cliente)
    {
        this.cliente = cliente;
    }

    public String getFechadevolucion ()
    {
        return fechadevolucion;
    }

    public void setFechadevolucion (String fechadevolucion)
    {
        this.fechadevolucion = fechadevolucion;
    }

    public String getFechaprestamo ()
    {
        return fechaprestamo;
    }

    public void setFechaprestamo (String fechaprestamo)
    {
        this.fechaprestamo = fechaprestamo;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [libro = "+libro+", cliente = "+cliente+", fechadevolucion = "+fechadevolucion+", fechaprestamo = "+fechaprestamo+"]";
    }
}
