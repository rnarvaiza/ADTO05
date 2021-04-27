package pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="prestamos")
@XmlAccessorType(XmlAccessType.FIELD)
public class Prestamos
{
    @XmlElement(name = "prestamo")
    private List<Prestamo> prestamo;

    public List<Prestamo> getPrestamo ()
    {
        return prestamo;
    }

    public void setPrestamo (List<Prestamo> prestamo)
    {
        this.prestamo = prestamo;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [prestamo = "+prestamo+"]";
    }
}

