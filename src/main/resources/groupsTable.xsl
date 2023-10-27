<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:j="http://javaops.ru">
    <xsl:param name="project"/>
    <xsl:template match="j:Payload/j:Groups">
        <html>
            <body>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th style="text-align:left">Name</th>
                        <th style="text-align:left">Status</th>
                    </tr>
                    <xsl:for-each select="j:Group[@project=$project]">
                        <tr>
                            <td>
                                <xsl:value-of select="j:name"/>
                            </td>
                            <td>
                                <xsl:value-of select="@status"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="text()"/>
</xsl:stylesheet>