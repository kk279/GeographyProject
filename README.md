In my spring boot application, I am trying to store spatial data using spring data with hibernate (jpa).
An AttributeConverter allows to convert between the database and the Java representation of an attribute.
I used the Spring Data Point class for simplicity, but the code should look very similar for the JTS analogous class.

Please, be aware that we are converting from byte[] and not from the Geography type itself: internally, the SQL Server JDBC driver handles the information as UDT and type VARBINARY.

Then, annotate the corresponding field in your entity with this new AttributeConverter:

@Convert(converter = SQLServerGeographyAttributeConverter.class)
@Column(columnDefinition = "geography")
public Point point;
