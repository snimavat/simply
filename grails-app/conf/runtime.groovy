import org.simply.cms.block.RichTextBlock
import org.simply.cms.pages.BlogIndexPage
import org.simply.cms.pages.BlogPage
import org.simply.cms.pages.HomePage

simply {
	cms {
		pageClasses = [HomePage, BlogPage, BlogIndexPage]
		blocks = [RichTextBlock]
	}
}