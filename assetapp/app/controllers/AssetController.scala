package controllers
import javax.inject._
import play.api.mvc._




class AssetController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action { 
    implicit request: Request[AnyContent] => Ok("Welcome to the Asset Management Application!")
  }
  
}
