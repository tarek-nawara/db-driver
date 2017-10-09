package edu.db.driver.physical.serializers

import edu.db.driver.data.DBConfiguration
import edu.db.driver.physical.data.Relation
import scala.xml.Elem
import scala.collection.mutable.ListBuffer

/** Logic for serializing Database tables to xml files
  *
  * @author Tarek Nawara
  */
object RelationSerializer {
  def serialize(relation: Relation)(implicit config: DBConfiguration): Unit = {
    def serializeRecord(header: List[(String, String)], record: Relation#Record): Elem =
      <Record>
        { for ((key, _) <- header) yield <column key={ key } value={ record(key) }/> }
      </Record>
    def serializeHeader(header: List[(String, String)]): Elem =
      <header>
        { for ((key, value) <- header) yield <column name={ key } type={ value }/> }
      </header>

    val xmlElem =
      <table name={ relation.name }>
        { serializeHeader(relation.header) }
        <Records>
          { for (record <- relation.records) yield serializeRecord(relation.header, record) }
        </Records>
      </table>
    scala.xml.XML.save(config.path + s"\\${relation.name}.xml", xmlElem)
  }
}