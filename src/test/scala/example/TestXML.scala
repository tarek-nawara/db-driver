package example

import edu.db.driver.physical.data.Relation
import edu.db.driver.data.DBConfiguration
import edu.db.driver.physical.serializers.RelationSerializer
import edu.db.driver.physical.parsers.RelationParser
import edu.db.driver.data.ddl.CreateDB

object TestXML {
  def main(args: Array[String]): Unit = {
    val relation = new Relation("employee", Map("firstName" -> "string", "lastName" -> "string").toList)
    implicit val config = new DBConfiguration()
    val a = new CreateDB("company")
    a.execute()
    val record = Map("firstName" -> "tarek", "lastName" -> "nawara")
    relation.records += record
    RelationSerializer.serialize(relation)
    val t = RelationParser.parse("employee")
    println(t)
  }
}