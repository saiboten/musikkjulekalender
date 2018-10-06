import { Block } from 'jsxstyle';

var React = require('react');
var debug = require('debug')('AdminDay');
var moment = require('moment');
var adminDayAction = require('../../actions/AdminDayAction');
var DatePicker = require('react-datepicker');
var AdminDaySolution = require('./AdminDaySolution');
var DateHeader = require('../DateHeader.jsx');
var SongAudio =require('../days/day/SongAudio');

var Dropzone = require('react-dropzone');
var request = require('superagent');

require('react-datepicker/dist/react-datepicker.css');

var AdminDay = React.createClass({

    componentDidMount() {
        debug("this.day", this.props.day);
    },

    getInitialState() {
        return {
            description: this.props.day.description,
            optionalSolutionVideo: this.props.day.optionalSolutionVideo,
            solutionArtist: this.props.day.solutionArtist,
            solutionSong: this.props.day.solutionSong,
            link: this.props.day.link,
            revealDate: moment(this.props.day.revealDate),
            solutionDate: moment(this.props.day.solutionDate),
            addSolution: "",
            confirmDelete: false,
            file: {},
            feedback: ""
        }
    },

    changeRevealDate: function(date) {
        this.setState({
            revealDate: date
        });
        debug("New date: ", date);
    },

    changeSolutionDate: function(date) {
        this.setState({
            solutionDate: date
        });
        debug("New date: ", date);
    },

    changeDescription: function(event) {
        this.setState({description: event.target.value});
    },

    changeSolutionArtist: function(event) {
        this.setState({solutionArtist: event.target.value});
    },

    changeSolutionSong: function(event) {
        this.setState({solutionSong: event.target.value});
    },

    changeOptionalSolutionVideo: function(event) {
        this.setState({optionalSolutionVideo: event.target.value});
    },

    changeLink: function(event) {
        this.setState({link: event.target.value});
    },

    saveChanges: function() {
        var saveObject = {
            link: this.state.link,
            description: this.state.description,
            optionalSolutionVideo: this.state.optionalSolutionVideo,
            solutionArtist: this.state.solutionArtist,
            solutionSong: this.state.solutionSong,
            revealDate: this.state.revealDate.valueOf(),
            solutionDate: this.state.solutionDate.valueOf(),
            revealDateAsString: this.state.revealDate.format("YYYY-MM-DD"),
            id: this.props.day.id
        };

        debug("Save object: ", saveObject);
        adminDayAction.updateDay(saveObject);
    },

    addSolutionChange: function(e) {
        debug("Updating state: ", e.target.value);
        this.setState({
            addSolution: e.target.value
        })
    },

    addSolution: function(e) {
        debug("Adding solution: ", this.state.addSolution);
        if(this.state.addSolution != "") {
          var solutionObject = {};
          solutionObject.solution = this.state.addSolution;
          solutionObject.id = this.props.day.id;
          adminDayAction.addSolution(solutionObject);
          this.setState({
              addSolution: ""
          })
        }
        else {
            this.setState({
              feedback: "Løsning er tom!"
            })
        }
    },

    createMarkup() {
        debug('Creating markup', this.state.optionalSolutionVideo);
        return {__html: this.state.optionalSolutionVideo};
    },

    deleteDay() {
      if(this.state.confirmDelete) {
          adminDayAction.deleteDay(this.props.day.id);
      }
      else {
          this.setState({
              confirmDelete: true
          })
      }
    },

    onDrop: function (files) {
       debug('Received files: ', files);
       this.setState({
           file: files[0]
       })
   },

   upload() {
        var req = request.post('/admin/upload/' + this.props.day.revealDateAsString);
        req.query({ filename: this.state.file.name });
        req.attach("file", this.state.file);

        var songname = this.state.file.name;
        var that = this;

        req.end(function(err, res) {
            debug("err, ", err);
            debug("res, ", res);
            if(res) {
              // Call stuff
                adminDayAction.getDays();
            }


            that.setState({
                file: {}
            })
        });
    },

    abortUpload() {
        this.setState({
            file: {}
        })
    },

    render() {
        var day = "";
        var fileupload = (<p>Fil som blir lastet opp: {this.state.file.name}<button onClick={this.upload}>Last opp</button><button onClick={this.abortUpload}>Avbryt opplasting</button></p>);

        return (
            <Block border="1px solid black" borderRadius="5px" margin="5px" padding="5px" className="admin-day__container">
                <DateHeader unixDate={this.props.day.revealDateAsString}></DateHeader>

                <div>
                    <table>
                        <tbody>
                            <tr>
                                <td>Kalender åpner</td>
                                <td><DatePicker selected={this.state.revealDate} onChange={this.changeRevealDate} /></td>
                            </tr>
                            <tr>
                                <td>Dag avsluttes</td>
                                <td><DatePicker selected={this.state.solutionDate} onChange={this.changeSolutionDate} /></td>
                            </tr>
                            <tr>
                                <td>Beskrivelse</td>
                                <td><textarea className="admin-day__description-textarea" type="text" onChange={this.changeDescription} value={this.state.description}/></td>
                            </tr>
                            <tr>
                                <td>Artist</td>
                                <td><input className="admin-day__input" type="text" onChange={this.changeSolutionArtist} value={this.state.solutionArtist}/></td>
                            </tr>
                            <tr>
                                <td>Sang</td>
                                <td><input className="admin-day__input" type="text" onChange={this.changeSolutionSong} value={this.state.solutionSong}/></td>
                            </tr>
                            <tr>
                                <td>Video</td>
                                <td><p>Gå inn på youtubevideoen, trykk "share", velg Embed, og lim inn hele sulamitten i feltet under</p><input className="admin-day__input" type="text" onChange={this.changeOptionalSolutionVideo} value={this.state.optionalSolutionVideo}/></td>
                            </tr>
                            <tr>
                                <td>Link</td>
                                <td><input className="admin-day__input" readOnly disabled type="text" onChange={this.changeLink} value={this.state.link}/></td>
                            </tr>
                        </tbody>
                    </table>
                    <ul>
                        {
                            this.props.day.solutions.map(function(solution) {
                                debug("This props:", this);
                                return <AdminDaySolution key={solution.solution} dayId={this.props.day.id} solution={solution}></AdminDaySolution>
                            },this)
                        }
                    </ul>
                    <input type="text" value={this.state.addSolution} placeholder="Legg til løsning" onChange={this.addSolutionChange} /><button onClick={this.addSolution}>Legg til</button>

                    <Dropzone ref="dropzone" className="dropzone" onDrop={this.onDrop}>
                      <p>Last opp låt</p>
                      </Dropzone>
                      {this.state.file.name ? fileupload : ""}

                    <p>
                        <span>
                            {this.state.optionalSolutionVideo ?
                                <span className="youtube"
                                      dangerouslySetInnerHTML={this.createMarkup()}></span> : ""}</span>
                    </p>
                    <p>
                        <button onClick={this.saveChanges}>Lagre endringer</button>
                        <button onClick={this.deleteDay}>Slett dag</button>{this.state.confirmDelete ? "Bekreft":""}
                        <p>{this.state.feedback}</p>
                    </p>

                    <SongAudio link={this.state.link} />
                </div>
            </Block>
        );
    }
});

module.exports = AdminDay;
