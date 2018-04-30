package ${package}.model;

<#list allTypes as t>
import ${t};
</#list>
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

/**
* author: ${author}
* ${now?date}
*/
@Data
@EqualsAndHashCode(callSuper=true)
public class ${table.className} extends BaseEntity  implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    <#list fields as f>
    /**
     * ${f.comment}
    */ 
    private ${f.type} ${f.field};
    
    </#list>
     
}
