package steps_definitions

import io.cucumber.datatable.DataTable
import io.cucumber.java.pt.Dado
import io.cucumber.java.pt.Então
import io.cucumber.java.pt.Quando
import org.junit.Assert
import service.Service
import utils.Helpers.Companion.getIntegerNumber
import utils.Helpers.Companion.getNewTitle

class Steps {
    var service: Service? = null
    private val randomId: Int = getIntegerNumber(4)
    private val randomTitle: String = getNewTitle()
    private val alteredId = 13

    @Dado("que estou na aplicação")
    fun que_estou_na_aplicacao() {
        service = Service()
    }

    @Quando("feito requisicao para criar uma nova atividade")
    fun feito_requisicao_para_criar_uma_nova_atividade() {
        service!!.postRequest("activities", "activity.json")
    }

    @Quando("feito requisicao para obter todas as atividades")
    fun feito_requisicao_para_obter_todas_as_atividades() {
        service!!.getRequest("activities")
    }

    @Quando("feito requisicao para obter dados de uma atividade especifica")
    fun feito_requisicao_para_obter_dados_de_uma_atividade_especifica(dataTable: DataTable) {
        service!!.getRequestWithPathParam("find.activity", dataTable.row(1).get(0))
    }

    @Então("os dados da atividade devem ser apresentados em conformidade")
    fun os_dados_da_atividade_devem_ser_apresentados_em_conformidade(dataTable: DataTable) {
        Assert.assertEquals(200, service!!.getStatusCode())
        Assert.assertEquals(service!!.getBody("id"), dataTable.row(1).get(0))
        Assert.assertEquals(service!!.getBody("title"), dataTable.row(1).get(1))
        Assert.assertTrue(service!!.getBody("dueDate").isNotBlank())
        Assert.assertEquals(service!!.getBody("completed"), dataTable.row(1).get(2))
    }


    @Então("deve ser retornado sucesso na criação")
    fun deve_ser_retornado_sucesso_na_criacao(dataTable: DataTable) {
        Assert.assertEquals(200, service!!.getStatusCode())
        Assert.assertEquals(service!!.getBody("id"), dataTable.row(1).get(0))
        Assert.assertEquals(service!!.getBody("title"), dataTable.row(1).get(1))
        Assert.assertEquals(service!!.getBody("dueDate"), dataTable.row(1).get(2))
        Assert.assertEquals(service!!.getBody("completed"), dataTable.row(1).get(3))
    }

    @Então("verificado se o contrato de activity esta em conformidade")
    fun verificado_se_o_contrato_de_activity_esta_em_conformidade() {
        service!!.checkContractType(Integer::class.javaObjectType, "id[0]")
        service!!.checkContractType(String::class.javaObjectType, "title[0]")
        service!!.checkContractType(String::class.javaObjectType, "dueDate[0]")
        service!!.checkContractType(Boolean::class.javaObjectType, "completed[0]")
    }
}