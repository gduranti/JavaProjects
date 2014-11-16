USE [unitunes]
GO

/****** Object:  Table [dbo].[MOVEMENTS]    Script Date: 11/16/2014 20:08:43 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[MOVEMENTS](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[date] [datetime2](7) NOT NULL,
	[description] [varchar](255) NOT NULL,
	[paymentType] [int] NULL,
	[source] [int] NOT NULL,
	[type] [int] NOT NULL,
	[value] [float] NOT NULL,
	[user_id] [bigint] NOT NULL,
	[media_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[MOVEMENTS]  WITH CHECK ADD  CONSTRAINT [FKE686258474420F6] FOREIGN KEY([media_id])
REFERENCES [dbo].[MEDIAS] ([id])
GO

ALTER TABLE [dbo].[MOVEMENTS] CHECK CONSTRAINT [FKE686258474420F6]
GO

ALTER TABLE [dbo].[MOVEMENTS]  WITH CHECK ADD  CONSTRAINT [FKE68625849BA489E] FOREIGN KEY([user_id])
REFERENCES [dbo].[USERS] ([id])
GO

ALTER TABLE [dbo].[MOVEMENTS] CHECK CONSTRAINT [FKE68625849BA489E]
GO
